package java.com.javahiringchallenge.dao;

import java.com.javahiringchallenge.model.*;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public class UserRoleDAO {
	@Autowired
	private SessionFactory sessionFactory;
	
	private Session getSession() {
		return sessionFactory.getCurrentSession();
	}
	
	@Autowired
	private UserDAO userDAO = new UserDAO();
	private RoleDAO roleDAO = new RoleDAO();
	
	public String saveUserRole(UserRole userRole) {
		Long isSuccess = (Long)getSession().save(userRole);
		if(isSuccess >= 1){
			return "Success";
		}else{
			return "Error while Saving User Role";
		}
	}

	public boolean deleteUserRole(UserRole userRole) {
		getSession().delete(userRole);
		return true;
	}
	
	public boolean updateUserRole(UserRole userRole) {
		if(userRole != null) {
			getSession().update(userRole);
			return true;
		}
		return false;
	}

	@SuppressWarnings("unchecked")
	public List<UserRole> getUserRoleList() {
		return getSession().createQuery("from UserRole where isdeleted=false").list();
	}
	
	@SuppressWarnings("unchecked")
	public UserRole getUserRoleById(long id) {
		UserRole userRole = null;
		User user = null;
		Role role = null;
		List list = getSession().createQuery("from UserRole where id=" + id + "").list();
		if(list != null && list.size() == 1)
		{
			userRole = new UserRole();
			userRole.setId((Long) list.get(0));
			userRole.setUserid((Long)list.get(1));
			user = new User();
			user = userDAO.getUserById((Long)list.get(1));
			if(user != null) {
				userRole.setUser(user);
			}
			userRole.setRoleid((Long)list.get(2));
			role = new Role();
			role = roleDAO.getRoleById((Long)list.get(2));
			if(role != null) {
				userRole.setRole(role);
			}
			userRole.setDeleted((Boolean)list.get(3));
			userRole.setChecked((Boolean)list.get(4));
			return userRole;
		}
		return null;
	}
	
	@SuppressWarnings("unchecked")
	public UserRole getUserRoleByUserIdAndRoleId(long userid, long roleid) {
		UserRole userRole = null;
		User user = null;
		Role role = null;
		List list = getSession().createQuery("from UserRole " +
				" where userid=" + userid + 
				" and roleid=" + roleid + "").list();
		if(list != null && list.size() == 1)
		{
			userRole = new UserRole();
			userRole.setId((Long) list.get(0));
			userRole.setUserid((Long)list.get(1));
			user = new User();
			user = userDAO.getUserById((Long)list.get(1));
			if(user != null) {
				userRole.setUser(user);
			}
			userRole.setRoleid((Long)list.get(2));
			role = new Role();
			role = roleDAO.getRoleById((Long)list.get(2));
			if(role != null) {
				userRole.setRole(role);
			}
			userRole.setDeleted((Boolean)list.get(3));
			userRole.setChecked((Boolean)list.get(4));
			return userRole;
		}
		return null;
	}

	public UserRole createUserRole(User user, Role role) {
		UserRole userRole = null;
		if(user != null && role != null)
		{
			userRole = new UserRole();
			userRole.setUserid((Long) user.getId());
			userRole.setUser(user);
			userRole.setRoleid((Long) role.getId());
			userRole.setRole(role);
			userRole.setDeleted(false);
			userRole.setChecked(false);
			saveUserRole(userRole);
			return userRole;
		}
		return null;
	}
}