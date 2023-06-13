package java.com.javahiringchallenge.controller;

import java.com.javahiringchallenge.dao.*;
import java.com.javahiringchallenge.model.*;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping(value="/product")
public class ProductController {
	@Autowired
	private ProductDAO productDAO = new ProductDAO();
	private BasketDAO basketDAO = new BasketDAO();
	private OrderDAO orderDAO = new OrderDAO();
	
	@RequestMapping(value="/deleteProduct")
	@ResponseBody
	//@PreAuthorize("hasRole('ADMIN')")
	public String deleteProduct(@RequestParam(value="ProductId") long id) {
		try {			
			Product product = new Product();
			product = productDAO.getProductById(id);
			product.setDeleted(true);
			product.setBasketed(false);
			List<Basket> basketList = new ArrayList<Basket>();
			Order order = null;
			Basket basket = null;
			basketList = basketDAO.getBasketListByProductId(product.getId());
			for (int i = 0; i < basketList.size(); i++) {
				order = new Order();
				basket = new Basket();
				basket = basketList.get(i);
				basket.setDeleted(true);
				basket.setOrdered(false);
				order = basket.getOrder();
				order.setDeleted(true);
				order.setSended(false);
				orderDAO.updateOrder(order);
				basketDAO.updateBasket(basket);
			}
			productDAO.updateProduct(product);
		} catch (Exception e) {
			return e.getMessage();
		}
		return "Product succesfully deleted!";
	}
	
	@RequestMapping(value="/createProduct")
	@ResponseBody
	//@PreAuthorize("hasRole('ADMIN')")
	public String createProduct( 
			@RequestParam(value="name") String name, 
			@RequestParam(value="mark") String mark,
			@RequestParam(value="stockCode") String stockCode, 
			@RequestParam(value="productGroup") long productGroupId,
			@RequestParam(value="price") Double price, 
			@RequestParam(value="stockQuantity") Integer stockQuantity,
			Model model) {
		try {
			Product product = new Product();
			//product.setId(id);
			product.setName(name);
			product.setMark(mark);
			product.setStockCode(stockCode);
			product.setProductGroupId(productGroupId);
			ProductGroup productGroup = new ProductGroup();
			productGroup = productDAO.getProductGroupById(productGroupId);
			if (productGroup != null) {
				product.setProductGroup(productGroup);
			}
			product.setPrice(price);
			product.setStockQuantity(stockQuantity);
			product.setDeleted(false);
			productDAO.saveProduct(product);
			model.addAttribute(product);
		} catch (Exception e) {
			return e.getMessage();
		}
		return "Product succesfully created!";
	}
	
	@RequestMapping(value="/editProduct")
	@ResponseBody
	public String updateProduct(
			@RequestParam(value="id", required=true) long id, 
			@RequestParam(value="name") String name, 
			@RequestParam(value="mark") String mark,
			@RequestParam(value="stockCode") String stockCode, 
			@RequestParam(value="productGroup") long productGroupId,
			@RequestParam(value="price") Double price, 
			@RequestParam(value="stockQuantity") Integer stockQuantity,
			Model model) {
		try {
			Product product = new Product();
			product = productDAO.getProductById(id);
			if (product != null) {
				product.setId(id);
				product.setName(name);
				product.setMark(mark);
				product.setStockCode(stockCode);
				product.setProductGroupId(productGroupId);
				ProductGroup productGroup = new ProductGroup();
				productGroup = productDAO.getProductGroupById(productGroupId);
				if (productGroup != null) {
					product.setProductGroup(productGroup);
				}
				product.setPrice(price);
				product.setStockQuantity(stockQuantity);
				product.setDeleted(false);
				productDAO.updateProduct(product);
				model.addAttribute(product);
			} else if(product == null) {
				return "Product is not found!";
			}
		} catch (Exception e) {
			return e.getMessage();
		}
		return "Product succesfully updated!";
	}
	
	@RequestMapping(value="/listProduct")
	@ResponseBody
	public List<Product> listProduct() {
		try {
			return productDAO.getProductList();
		} catch (Exception e) {
			return null;
		}
	}
	
	@RequestMapping(value="/listProductByGroup")
	@ResponseBody
	public List<Product> listProductByGroup(
			@RequestParam(value="productGroup") long productGroupId) {
		try {
			return productDAO.listProductByGroup(productGroupId);
		} catch (Exception e) {
			return null;
		}
	}
	
	@RequestMapping(value="/viewProduct")
	@ResponseBody
	public Product viewProduct(
			@RequestParam(value="productId") Long id, Model model) {
		try {
			Product product = new Product();
			product = productDAO.getProductById(id);
			model.addAttribute(product);
			return product;
		} catch (Exception e) {
			return null;
		}
	}
}