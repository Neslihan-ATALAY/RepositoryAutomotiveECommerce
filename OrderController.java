package java.com.javahiringchallenge.controller;

import java.com.javahiringchallenge.dao.*;
import java.com.javahiringchallenge.model.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping(value="/order")
public class OrderController {
	@Autowired
	private OrderDAO orderDAO = new OrderDAO();
	private BasketDAO basketDAO = new BasketDAO();
	private ProductDAO productDAO = new ProductDAO();
	private UserDAO userDAO = new UserDAO();
	private CardInfoDAO cardInfoDAO = new CardInfoDAO();
	private CityDAO cityDAO = new CityDAO();
	
	@RequestMapping(value="/deleteOrder")
	@ResponseBody
	public String deleteOrder(@RequestParam(value="OrderId") long id) {
		try {
			Order order = new Order();
			order = orderDAO.getOrderById(id);
			order.setDeleted(true);
			Basket basket = new Basket();
			basket = basketDAO.getBasketById(order.getBasketid());
			Product product = new Product();
			product = productDAO.getProductById(basket.getProductid());
			if(order.isDeleted() == true && basket.isDeleted() == false && basket.isOrdered() == true)  {
				product.setStockQuantity(product.getStockQuantity() + basket.getQuantity());
				product.setBasketed(false);
				basket.setDeleted(true);
				basket.setOrdered(false);
				order.setSended(false);
			}
			orderDAO.updateOrder(order);
			basketDAO.updateBasket(basket);
			productDAO.updateProduct(product);
		} catch (Exception e) {
			return e.getMessage();
		}
		return "Order succesfully deleted!";
	}
	
	@RequestMapping(value="/order", method = RequestMethod.GET)
	@ResponseBody
	public String order(Model model) {
		try {
			return "createOrder";
		} catch (Exception e) {
			return null;
		}
	}
	
	@RequestMapping(value="/createOrder", method = RequestMethod.POST)
	@ResponseBody
	public String createOrder(
			@RequestParam(value="userId") long userId, 
			@RequestParam(value="basketId") long basketId,
			@RequestParam(value="nameSurname") String nameSurname, 
			@RequestParam(value="address") String address,
			@RequestParam(value="city") String city, 
			@RequestParam(value="telephone") String telephone,
			//@RequestParam(value="cardInfoId") long cardInfoId,
			@RequestParam(value="cardNumber") String cardNumber,
			@RequestParam(value="cardLastDate") String cardLastDate,
			@RequestParam(value="cardCvc") String cardCvc,
			Model model) {
		try {
			Order order = new Order();
			order.setUserid(userId);
			User user = new User();
			user = userDAO.getUserById(userId);
			if(user != null) {
				order.setUser(user);
			}
			order.setBasketid(basketId);
			Basket basket = new Basket();
			basket = basketDAO.getBasketById(basketId);
			if(basket != null) {
				order.setBasket(basket);
				basket.setOrdered(true);
			}
			order.setNameSurname(nameSurname);
			order.setAddress(address);			
			City City = new City();
			City = cityDAO.getCityByName(city);
			if(city != null) {
				order.setCity(City);
			}
			order.setCityid(order.getCity().getId());
			order.setTelephone(telephone);
			DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
			Calendar calender = Calendar.getInstance();
			order.setDate(dateFormat.format(calender.getTime()));
			order.setTotalPrice(basket.getTotalPrice());
			//order.setCardInfoid(cardInfoId);
			CardInfo cardInfo = new CardInfo();
			//cardInfo = cardInfoDAO.getCardInfoById(cardInfoId);
			cardInfo.setCardNumber(cardNumber);
			//cardInfo.setCardLastDate(cardLastDate);
			cardInfo.setCvc(cardCvc);
			cardInfoDAO.saveCardInfo(cardInfo);
			if(cardInfo != null) {
				order.setCardInfo(cardInfo);
			}
			order.setSended(false);
			order.setDeleted(false);
			orderDAO.saveOrder(order);
			basket.setOrder(order);
			basketDAO.updateBasket(basket);
			model.addAttribute(order);
		} catch (Exception e) {
			return e.getMessage();
		}
		return "Order succesfully created!";
	}
	
	@RequestMapping(value="/editOrder")
	@ResponseBody
	public String updateOrder(
			@RequestParam(value="orderId") long id,
			@RequestParam(value="userId") long userId, 
			@RequestParam(value="basketId") long basketId,
			@RequestParam(value="nameSurname") String nameSurname, 
			@RequestParam(value="address") String address,
			@RequestParam(value="cityId") long cityId, 
			@RequestParam(value="telephone") String telephone,
			@RequestParam(value="totalPrice") Double totalPrice,
			@RequestParam(value="cardInfoId") long cardInfoId,
			Model model) {
		try {
			Order order = null;
			User user = null;
			Basket basket = null;
			City city = null;
			CardInfo cardInfo = null;
			if(id != 0) {
				order = orderDAO.getOrderById(id);
			}
			if (order != null) {
				order.setId(id);
				order.setUserid(userId);
				user = new User();
				user = userDAO.getUserById(userId);
				if(user != null) {
					order.setUser(user);
				}
				order.setBasketid(basketId);
				basket = new Basket();
				basket = basketDAO.getBasketById(basketId);
				if(basket != null) {
					order.setBasket(basket);
					basket.setOrdered(true);
				}	
				order.setNameSurname(nameSurname);
				order.setAddress(address);
				order.setCityid(cityId);
				city = new City();
				city = cityDAO.getCityById(cityId);
				if(city != null) {
					order.setCity(city);
				}
				order.setTelephone(telephone);
				DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
				Calendar calender = Calendar.getInstance();
				order.setDate(dateFormat.format(calender.getTime()));
				order.setTotalPrice(totalPrice);
				order.setCardInfoid(cardInfoId);
				cardInfo = new CardInfo();
				cardInfo = cardInfoDAO.getCardInfoById(cardInfoId);
				if(cardInfo != null) {
					order.setCardInfo(cardInfo);
				}
				order.setSended(false);
				order.setDeleted(false);
				orderDAO.updateOrder(order);
				basketDAO.updateBasket(basket);
				model.addAttribute(order);
			} else if (order == null) {
				return "Order is not found!";
			}
		} catch (Exception e) {
			return e.getMessage();
		}
		return "Order succesfully updated!";
	}
	
	@RequestMapping(value="/listOrderByUser")
	@ResponseBody
	public List<Order> listOrderByUserId(@RequestParam(value="UserId") long userid) {
		try {
			return orderDAO.getOrderListByUserId(userid);
		} catch (Exception e) {
			return null;
		}
	}
	
	@RequestMapping(value="/listOrder")
	@ResponseBody
	public List<Order> listOrder() {
		try {
			return orderDAO.getOrderList();
		} catch (Exception e) {
			return null;
		}
	}
	
	@RequestMapping(value="/viewOrder")
	@ResponseBody
	public Order viewOrder(@RequestParam(value="OrderId") long id, Model model) {
		try {
			Order order = new Order();
			order = orderDAO.getOrderById(id);
			model.addAttribute(order);
			return orderDAO.getOrderById(id);
		} catch (Exception e) {
			return null;
		}
	}
	
	@RequestMapping(value="/checkUser")
	@ResponseBody
	//@PreAuthorize("hasRole('ADMIN')")
	public Order checkOrder(
			@RequestParam(value="orderId") long id, Model model) {
		try {
			Order order = new Order();
			order = orderDAO.getOrderById(id);
			if(order != null)
			{
				order.setChecked(true);
				order.setDeleted(false);
				orderDAO.updateOrder(order);
				model.addAttribute(order);
			}
			return order;
		} catch (Exception e) {
			return null;
		}
	}
	
	@RequestMapping(value="/notCheckUser")
	@ResponseBody
	//@PreAuthorize("hasRole('ADMIN')")
	public Order notCheckOrder(
			@RequestParam(value="orderId") long id, Model model) {
		try {
			Order order = new Order();
			order = orderDAO.getOrderById(id);
			if(order != null)
			{
				order.setChecked(false);
				order.setDeleted(true);
				orderDAO.updateOrder(order);
				model.addAttribute(order);
			}
			return order;
		} catch (Exception e) {
			return null;
		}
	}
}