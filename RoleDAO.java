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
public class RoleDAO {
	@Autowired
	private SessionFactory sessionFactory;
	
	private Session getSession() {
		return sessionFactory.getCurrentSession();
	}
	
	public String saveRole(Role role) {
		Long isSuccess = (Long)getSession().save(role);
		if(isSuccess >= 1){
			return "Success";
		}else{
			return "Error while Saving Role";
		}
	}

	public boolean deleteRole(Role role) {
		getSession().delete(role);
		return true;
	}
	
	public boolean updateRole(Role role) {
		if(role != null) {
			getSession().update(role);
			return true;
		}
		return false;
	}

	@SuppressWarnings("unchecked")
	public List<Role> getRoleList() {
		return getSession().createQuery("from Role where isdeleted=false").list();
	}
	
	//!!!!!!!!! CHECK PLEASE !!!!!!!!!!!!!!!!!!!!
	@SuppressWarnings("unchecked")
	public List<Role> getRoleListByUserId(long userid) {
		List<Role> listRole;
		Role role = null;
		List list = getSession().createQuery("from Role r" +
				" inner join UserRole ur on ur.roleid = r.id " +
				" where ur.userid=" + userid + " and r.isdeleted=false").list();
		if(list != null && list.size() > 0) {
			listRole = new ArrayList<Role>();
			for (int i = 0; i < list.size(); i++) {
				role = new Role();
				role.setId((Long)list.get(0));
				role.setRoleName(list.get(1).toString());
				role.setDeleted((Boolean) list.get(2));
				listRole.add(role);
			}
			return listRole;
		}
		return null;
	}
	
	@SuppressWarnings("unchecked")
	public Role getRoleById(long id) {
		Role role = null;
		List list = getSession().createQuery("from Role where id=" + id + "").list();
		if(list != null && list.size() == 1)
		{
			role = new Role();
			role.setId((Long) list.get(0));
			role.setRoleName(list.get(1).toString());
			role.setDeleted((Boolean)list.get(2));
			return role;
		}
		return null;
	}
}