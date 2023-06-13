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
public class UserDAO {
	@Autowired
	private SessionFactory sessionFactory;
	
	private Session getSession() {
		return sessionFactory.getCurrentSession();
	}
	
	public String saveUser(User user) {
		Long isSuccess = (Long)getSession().save(user);
		if(isSuccess >= 1){
			return "Success";
		}else{
			return "Error while Saving User";
		}
	}

	public boolean deleteUser(User user) {
		getSession().delete(user);
		return true;
	}
	
	public boolean updateUser(User user) {
		if(user != null) {
			getSession().update(user);
			return true;
		}
		return false;
	}

	@SuppressWarnings("unchecked")
	public List<User> getUserList() {
		return getSession().createQuery("from User where isdeleted=false").list();
	}
	
	@SuppressWarnings("unchecked")
	public List<User> getUserListByRoleId(long roleid) {
		List<User> listUser;
		User user = null;
		List list = getSession().createQuery("from User u" +
				" inner join UserRole ur on ur.userid = u.id " +
				" where ur.roleid=" + roleid + " and u.isdeleted=false").list();
		if(list != null && list.size() > 0) {
			listUser = new ArrayList<User>();
			for (int i = 0; i < list.size(); i++) {
				user = new User();
				user.setId((Long)list.get(0));
				user.setUserName(list.get(1).toString());
				user.setPassword(list.get(2).toString());
				user.setName(list.get(3).toString());
				user.setSurname(list.get(4).toString());
				user.setDeleted((Boolean) list.get(5));
				user.setChecked((Boolean) list.get(6));
				listUser.add(user);
			}
			return listUser;
		}
		return null;
	}
	
	@SuppressWarnings("unchecked")
	public User getUserById(long id) {
		User user = null;
		List list = getSession().createQuery("from User where id=" + id + "").list();
		if(list != null && list.size() == 1)
		{
			user = new User();
			user.setId((Long) list.get(0));
			user.setUserName(list.get(1).toString());
			user.setPassword(list.get(2).toString());
			user.setName(list.get(3).toString());
			user.setSurname(list.get(4).toString());
			user.setDeleted((Boolean) list.get(5));
			user.setChecked((Boolean) list.get(6));
			return user;
		}
		return null;
	}
	
	@SuppressWarnings("unchecked")
	public User getUserByUserName(String userName) {
		User user = null;
		List list = getSession().createQuery("from User where ischecked=true and isdeleted=false " +
				" and username='" + userName + "'").list();
		if(list != null && list.size() == 1)
		{
			user = new User();
			user.setId((Long) list.get(0));
			user.setUserName(list.get(1).toString());
			user.setPassword(list.get(2).toString());
			user.setName(list.get(3).toString());
			user.setSurname(list.get(4).toString());
			user.setDeleted((Boolean) list.get(5));
			user.setChecked((Boolean) list.get(6));
			return user;
		}
		return null;
	}
}