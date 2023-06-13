package java.com.javahiringchallenge.controller;

import java.com.javahiringchallenge.dao.*;
import java.com.javahiringchallenge.model.*;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping(value="/basket")
public class BasketController {
	@Autowired
	private BasketDAO basketDAO = new BasketDAO();
	private ProductDAO productDAO = new ProductDAO();
	private UserDAO userDAO = new UserDAO();
	
	@RequestMapping(value="/deleteBasket")
	@ResponseBody
	public String deleteBasket(@RequestParam(value="BasketId") long id) {
		try {
			Basket basket = new Basket();
			basket = basketDAO.getBasketById(id);
			basket.setDeleted(true);
			basket.setOrdered(false);
			Product product = new Product();
			product = productDAO.getProductById(basket.getProductid());
			Order order = new Order();
			if(basket.isDeleted == true && basket.isOrdered() == false)  {
				product.setStockQuantity(product.getStockQuantity() + basket.getQuantity());
				product.setBasketed(false);
				order = basket.getOrder();
				order.setSended(false);
				order.setDeleted(true);
			}
			basketDAO.updateBasket(basket);
			productDAO.updateProduct(product);
		} catch (Exception e) {
			return e.getMessage();
		}
		return "Basket succesfully deleted!";
	}
	
	@RequestMapping(value="/createBasket")
	@ResponseBody
	public String createBasket(
			@RequestParam(value="hiddenUserId") long userId,
			@RequestParam(value="productId") long productId,
			@RequestParam(value="quantity") int quantity,
			Model model) {
		try {
			Basket basket = new Basket();
			basket.setUserid(userId);
			User user = new User();
			user = userDAO.getUserById(userId);
			if(user != null) {
				basket.setUser(user);
			}
			basket.setProductid(productId);
			Product product = new Product();
			product = productDAO.getProductById(productId);
			if(product != null) {
				basket.setProduct(product);
				product.setBasketed(true);
			}
			if(quantity != 0) {
				basket.setQuantity(quantity);
				if(product.isBasketed() == true) {
					product.setStockQuantity(product.getStockQuantity() - quantity);
				}
			}
			if(product != null && product.getPrice() != 0 && quantity != 0) {
				basket.setTotalPrice(product.getPrice() * quantity);
			}
			basket.setOrdered(false);
			basket.setDeleted(false);
			//basket.setOrder(new Order());
			basketDAO.saveBasket(basket);
			productDAO.updateProduct(product);
			model.addAttribute(basket);
		} catch (Exception e) {
			return e.getMessage();
		}
		return "Basket succesfully created!";
	}
	
	@RequestMapping(value="/editBasket")
	@ResponseBody
	public String updateBasket(
			@RequestParam(value="BasketId") long id,
			@RequestParam(value="hiddenUserId") long userId,
			@RequestParam(value="productId") long productId,
			@RequestParam(value="quantity") int quantity,
			Model model) {
		try {
			Basket basket = null;
			User user = null;
			Product product = null;
			if(id != 0) {
				basket = basketDAO.getBasketById(id);
			}
			if (basket != null) {
				basket.setId(id);
				basket.setUserid(userId);
				user = new User();
				user = userDAO.getUserById(userId);
				if(user != null) {
					basket.setUser(user);
				}
				basket.setProductid(productId);
				product = new Product();
				product = productDAO.getProductById(productId);
				if(product != null) {
					basket.setProduct(product);
					product.setBasketed(true);
				}
				if(quantity != 0) {
					basket.setQuantity(quantity);
					if(product.isBasketed() == true) {
						product.setStockQuantity(product.getStockQuantity() - quantity);
					}
				}
				if(product != null && product.getPrice() != 0 && quantity != 0) {
					basket.setTotalPrice(product.getPrice() * quantity);
				}
				basket.setOrdered(false);
				basket.setDeleted(false);
				basketDAO.updateBasket(basket);
				productDAO.updateProduct(product);
				model.addAttribute(basket);
			} else if (basket == null) {
				return "Basket is not found!";
			}
		} catch (Exception e) {
			return e.getMessage();
		}
		return "Basket succesfully updated!";
	}
	
	@RequestMapping(value="/listBasketByUser")
	@ResponseBody
	public List<Basket> listBasketByUserId(@RequestParam(value="userId") long userid) {
		try {
			return basketDAO.getBasketListByUserId(userid);
		} catch (Exception e) {
			return null;
		}
	}
	
	@RequestMapping(value="/listBasket")
	@ResponseBody
	public List<Basket> listBasket() {
		try {
			return basketDAO.getBasketList();
		} catch (Exception e) {
			return null;
		}
	}
	
	@RequestMapping(value="/viewBasket")
	@ResponseBody
	public Basket viewBasket(
			@RequestParam(value="basketId") long id, Model model) {
		try {
			Basket basket = new Basket();
			basket = basketDAO.getBasketById(id);
			model.addAttribute(basket);
			return basket;
		} catch (Exception e) {
			return null;
		}
	}
}