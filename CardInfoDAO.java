package java.com.javahiringchallenge.dao;

import java.com.javahiringchallenge.model.CardInfo;
import java.com.javahiringchallenge.model.User;
import java.sql.Date;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public class CardInfoDAO {
	@Autowired
	private SessionFactory sessionFactory;
	
	private Session getSession() {
		return sessionFactory.getCurrentSession();
	}
	
	@Autowired
	private UserDAO userDAO = new UserDAO();
	
	public String saveCardInfo(CardInfo cardInfo) {
		Long isSuccess = (Long)getSession().save(cardInfo);
		if(isSuccess >= 1){
			return "Success";
		}else{
			return "Error while Saving User";
		}
	}

	public boolean deleteCardInfo(CardInfo cardInfo) {
		getSession().delete(cardInfo);
		return true;
	}
	
	public boolean updateCardInfo(CardInfo cardInfo) {
		if(cardInfo != null) {
			getSession().update(cardInfo);
			return true;
		}
		return false;
	}

	@SuppressWarnings("unchecked")
	public List<CardInfo> getCardInfoList() {
		return getSession().createQuery("from CardInfo where isdeleted=false").list();
	}
	
	@SuppressWarnings("unchecked")
	public CardInfo getCardInfoById(long id) {
		CardInfo cardInfo = null;
		User user = null;
		List list = getSession().createQuery("from CardInfo where id=" + id + "").list();
		if(list != null && list.size() == 1)
		{
			cardInfo = new CardInfo();
			cardInfo.setId((Long)list.get(0));
			cardInfo.setUserid((Long)list.get(1));
			user = new User();
			user = userDAO.getUserById((Long)list.get(1));
			if(user != null) {
				cardInfo.setUser(user);
			}
			cardInfo.setCardNumber(list.get(2).toString());
			cardInfo.setCardLastDate((Date)list.get(3));
			cardInfo.setCvc(list.get(4).toString());
			cardInfo.setDeleted((Boolean)list.get(5));
			return cardInfo;
		}
		return null;
	}
}