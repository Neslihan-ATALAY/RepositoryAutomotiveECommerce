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
public class OrderDAO {
	@Autowired
	private SessionFactory sessionFactory;
	
	private Session getSession() {
		return sessionFactory.getCurrentSession();
	}
	
	@Autowired
	private UserDAO userDAO = new UserDAO();
	private BasketDAO basketDAO = new BasketDAO();
	private CardInfoDAO cardInfoDAO = new CardInfoDAO();
	private CityDAO cityDAO = new CityDAO();
	
	public String saveOrder (Order order) {
		Long isSuccess = (Long)getSession().save(order);
		if(isSuccess >= 1){
			return "Success";
		}else{
			return "Error while Saving Order";
		}
	}

	public boolean deleteOrder (Order order) {
		getSession().delete(order);
		return true;
	}
	
	public boolean updateOrder (Order order) {
		if(order != null) {
			getSession().update(order);
			return true;
		}
		return false;
	}
	
	@SuppressWarnings({ "unchecked" })
	public Order getOrderById(long id) {
		Order order = null;
		Basket basket = null;
		User user = null;
		City city = null;
		CardInfo cardInfo = null;
		List list = getSession().createQuery("from Order where id=" + id + "").list();
		if(list != null && list.size() == 1)
		{
			order  = new Order();
			order.setId((Long) list.get(0));
			order.setUserid((Long) list.get(1));
			user = new User();
			user = userDAO.getUserById((Long) list.get(1));
			if(user != null) {
				order.setUser(user);
			}
			order.setBasketid((Long) list.get(2));
			basket = new Basket();
			basket = basketDAO.getBasketById((Long) list.get(2));
			if(basket != null) {
				order.setBasket(basket);
			}
			order.setNameSurname(list.get(3).toString());
			order.setAddress(list.get(4).toString());
			order.setCityid((Long) list.get(5));
			city = new City();
			city = cityDAO.getCityById((Long) list.get(5));
			if(city != null) {
				order.setCity(city);
			}
			order.setTelephone(list.get(6).toString());
			order.setDate(list.get(7).toString());
			order.setTotalPrice((Double) list.get(8));
			order.setCardInfoid((Long) list.get(9));
			cardInfo = new CardInfo();
			cardInfo = cardInfoDAO.getCardInfoById((Long) list.get(9));
			if(cardInfo != null) {
				order.setCardInfo(cardInfo);
			}
			order.setSended((Boolean) list.get(10));
			order.setDeleted((Boolean) list.get(11));
			return order;
		}
		return null;
	}
	
	@SuppressWarnings({ "unchecked" })
	public List<Order> getOrderListByUserId(long userid) {
		Order order = null;
		User user = null;
		Basket basket = null;
		City city = null;
		CardInfo cardInfo = null;
		List<Order> listOrder = null;
		List list = getSession().createQuery("from Order where userid=" 
				+ userid 
				+ " and isdeleted=false "
				+ " and issended=false "
				).list();
		if(list != null && list.size() > 0)
		{
			listOrder = new ArrayList<Order>();
			for(int i = 0; i < list.size(); i++) {
				order = new Order();
				order.setId((Long) list.get(0));
				order.setUserid((Long) list.get(1));
				user = new User();
				user = userDAO.getUserById((Long) list.get(1));
				if(user != null) {
					order.setUser(user);
				}
				order.setBasketid((Long) list.get(2));
				basket = new Basket();
				basket = basketDAO.getBasketById((Long) list.get(2));
				if(basket != null) {
					order.setBasket(basket);
				}
				order.setNameSurname(list.get(3).toString());
				order.setAddress(list.get(4).toString());
				order.setCityid((Long) list.get(5));
				city = new City();
				city = cityDAO.getCityById((Long) list.get(5));
				if(city != null) {
					order.setCity(city);
				}
				order.setTelephone(list.get(6).toString());
				order.setDate(list.get(7).toString());
				order.setTotalPrice((Double) list.get(8));
				order.setCardInfoid((Long) list.get(9));
				cardInfo = new CardInfo();
				cardInfo = cardInfoDAO.getCardInfoById((Long) list.get(9));
				if(cardInfo != null) {
					order.setCardInfo(cardInfo);
				}
				order.setSended((Boolean) list.get(10));
				order.setDeleted((Boolean) list.get(11));
				listOrder.add(order);
			}
			return listOrder;
		}
		return null;
	}
	
	@SuppressWarnings({ "unchecked" })
	public List<Order> getOrderList() {
		return getSession().createQuery("from Order where " +
				" isdeleted=false and issended=false").list();
	}
}