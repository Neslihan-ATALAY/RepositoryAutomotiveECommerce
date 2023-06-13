package java.com.javahiringchallenge.dao;

import java.com.javahiringchallenge.model.*;
import java.util.ArrayList;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public class BasketDAO {
	@Autowired
	private SessionFactory sessionFactory;
	
	private Session getSession() {
		return sessionFactory.getCurrentSession();
	}
	
	@Autowired
	private UserDAO userDAO = new UserDAO();
	private ProductDAO productDAO = new ProductDAO();
	
	public String saveBasket (Basket basket) {
		Long isSuccess = (Long)getSession().save(basket);
		if(isSuccess >= 1){
			return "Success";
		}else{
			return "Error while Saving Product";
		}
	}

	public boolean deleteBasket (Basket basket) {
		getSession().delete(basket);
		return true;
	}
	
	public boolean updateBasket (Basket basket) {
		if(basket != null) {
			getSession().update(basket);
			return true;
		}
		return false;
	}
	
	@SuppressWarnings({ "unchecked" })
	public Basket getBasketById(long id) {
		Basket basket = null;
		User user = null;
		Product product = null;		
		List list = getSession().createQuery("from Basket where id=" + id + "").list();
		if(list != null && list.size() == 1)
		{
			basket = new Basket();
			basket.setId((Long) list.get(0));
			basket.setUserid((Long) list.get(1));
			user = new User();
			user = userDAO.getUserById((Long) list.get(1));
			if(user != null) {
				basket.setUser(user);
			}
			basket.setProductid((Long) list.get(2));
			product = new Product();
			product = productDAO.getProductById((Long) list.get(2));
			if(product != null) {
				basket.setProduct(product);
			}
			basket.setQuantity((Integer) list.get(3));
			basket.setTotalPrice((Double) list.get(5));
			basket.setOrdered((Boolean) list.get(6));
			basket.setDeleted((Boolean) list.get(7));
			return basket;
		}
		return null;
	}
	
	@SuppressWarnings({ "unchecked" })
	public List<Basket> getBasketListByUserId(long userid) {
		Basket basket = null;
		User user = null;
		Product product = null;
		List<Basket> listBasket = null;
		List list = getSession().createQuery("from Basket where userid=" 
				+ userid 
				+ " and isdeleted=false "
				+ " and isordered=false "
				).list();
		if(list != null && list.size() > 0)
		{
			listBasket = new ArrayList<Basket>();
			for(int i = 0; i < list.size(); i++) {
				basket = new Basket();
				basket.setId((Long) list.get(0));
				basket.setUserid((Long) list.get(1));
				user = new User();
				user = userDAO.getUserById((Long) list.get(1));
				if(user != null) {
					basket.setUser(user);
				}
				basket.setProductid((Long) list.get(2));
				product = new Product();
				product = productDAO.getProductById((Long) list.get(2));
				if(product != null) {
					basket.setProduct(product);
				}
				basket.setQuantity((Integer) list.get(3));
				basket.setTotalPrice((Double) list.get(5));
				basket.setOrdered((Boolean) list.get(6));
				basket.setDeleted((Boolean) list.get(7));
				listBasket.add(basket);
			}
			return listBasket;
		}
		return null;
	}
	
	@SuppressWarnings({ "unchecked" })
	public List<Basket> getBasketListByProductId(long productid) {
		Basket basket = null;
		User user = null;
		Product product = null;
		List<Basket> listBasket = null;
		List list = getSession().createQuery("from Basket where productid=" 
				+ productid 
				+ " and isdeleted=false "
				+ " and isordered=false "
				).list();
		if(list != null && list.size() > 0)
		{
			listBasket = new ArrayList<Basket>();
			for(int i = 0; i < list.size(); i++) {
				basket = new Basket();
				basket.setId((Long) list.get(0));
				basket.setUserid((Long) list.get(1));
				user = new User();
				user = userDAO.getUserById((Long) list.get(1));
				if(user != null) {
					basket.setUser(user);
				}
				basket.setProductid((Long) list.get(2));
				product = new Product();
				product = productDAO.getProductById((Long) list.get(2));
				if(product != null) {
					basket.setProduct(product);
				}
				basket.setQuantity((Integer) list.get(3));
				basket.setTotalPrice((Double) list.get(5));
				basket.setOrdered((Boolean) list.get(6));
				basket.setDeleted((Boolean) list.get(7));
				listBasket.add(basket);
			}
			return listBasket;
		}
		return null;
	}
	
	@SuppressWarnings({ "unchecked" })
	public List<Basket> getBasketList() {
		return getSession().createQuery("from Basket where " +
				" isdeleted=false and isordered=false").list();
	}
}