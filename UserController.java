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
public class UserController {
	@Autowired
	private UserDAO userDAO = new UserDAO();
	private RoleDAO roleDAO = new RoleDAO();
	private UserRoleDAO userRoleDAO = new UserRoleDAO();
	
	@RequestMapping(value="/deleteUser")
	@ResponseBody
	public String deleteUser(@RequestParam(value="userId") long id) {
		try {
			User user = new User();
			user = userDAO.getUserById(id);
			user.setDeleted(false);
			userDAO.updateUser(user);
		} catch (Exception e) {
			return e.getMessage();
		}
		return "User succesfully deleted!";
	}
	
	@RequestMapping(value="/createUser")
	@ResponseBody
	public String createUser(
			@RequestParam(value="userName") String username,
			@RequestParam(value="password") String password,
			@RequestParam(value="name") String name,
			@RequestParam(value="surname") String surname,
			@RequestParam(value="role") long roleid,
			Model model) {
		try {
			User user = new User();
			Role role = new Role();
			UserRole userRole = new UserRole();
			user.setUserName(username);
			user.setPassword(password);
			user.setName(name);
			user.setSurname(surname);			
			role = roleDAO.getRoleById(roleid);
			user.getRoles().add(role);
			user.setRoles(user.getRoles());
			user.setDeleted(false);
			user.setChecked(false);
			if(role != null) {
				userRole = userRoleDAO.createUserRole(user, role);
				user.getUserRoles().add(userRole);
				user.setUserRoles(user.getUserRoles());
			}
			userDAO.saveUser(user);
			model.addAttribute(user);
		} catch (Exception e) {
			return e.getMessage();
		}
		return "User succesfully created.";
	}
	
	@RequestMapping(value="/editUser")
	@ResponseBody
	public String updateUser(
			@RequestParam(value="userId") long id,
			@RequestParam(value="userName") String username,
			@RequestParam(value="password") String password,
			@RequestParam(value="name") String name,
			@RequestParam(value="surname") String surname,
			@RequestParam(value="role") long roleid,
			Model model) {
		try {
			User user = null;
			Role role = null;
			UserRole userRole = null;
			user = userDAO.getUserById(id);
			if(user != null) {
				user = new User();
				role = new Role();
				userRole = new UserRole();
				user.setId(id);
				user.setUserName(username);
				user.setPassword(password);
				user.setName(name);
				user.setSurname(surname);
				role = roleDAO.getRoleById(roleid);
				user.getRoles().add(role);
				user.setRoles(user.getRoles());
				user.setDeleted(false);
				user.setChecked(false);
				if(role != null) {
					userRole = userRoleDAO.createUserRole(user, role);
					user.getUserRoles().add(userRole);
					user.setUserRoles(user.getUserRoles());
				}
				userDAO.updateUser(user);
				model.addAttribute(user);
			} else if (user == null) {
				return "User is not found!";
			}
		} catch (Exception e) {
			return e.getMessage();
		}
		return "User succesfully updated.";
	}
	
	@RequestMapping(value="/listUser")
	@ResponseBody
	public List<User> listUser() {
		try {
			return userDAO.getUserList();
		} catch (Exception e) {
			return null;
		}
	}
	
	@RequestMapping(value="/listUserByRole")
	@ResponseBody
	public List<User> listUserByRoleId(@RequestParam(value="roleId") long roleid) {
		try {
			return userDAO.getUserListByRoleId(roleid);
		} catch (Exception e) {
			return null;
		}
	}
	
	@RequestMapping(value="/viewUser")
	@ResponseBody
	public User viewUser(@RequestParam(value="userId") long id, Model model) {
		try {
			User user = new User();
			user = userDAO.getUserById(id);
			model.addAttribute(user);
			return user;
		} catch (Exception e) {
			return null;
		}
	}
	
	@RequestMapping(value="/checkUser")
	@ResponseBody
	//@PreAuthorize("hasRole('ADMIN')")
	public User checkUser(
			@RequestParam(value="userId") long id, Model model) {
		try {
			User user = new User();
			user = userDAO.getUserById(id);
			if(user != null)
			{
				user.setChecked(true);
				user.setDeleted(false);
				userDAO.updateUser(user);
				model.addAttribute(user);
			}
			return user;
		} catch (Exception e) {
			return null;
		}
	}
	
	@RequestMapping(value="/notCheckUser")
	@ResponseBody
	//@PreAuthorize("hasRole('ADMIN')")
	public User notCheckUser(
			@RequestParam(value="userId") long id, Model model) {
		try {
			User user = new User();
			user = userDAO.getUserById(id);
			if(user != null)
			{
				user.setChecked(false);
				user.setDeleted(true);
				userDAO.updateUser(user);
				model.addAttribute(user);
			}
			return user;
		} catch (Exception e) {
			return null;
		}
	}
}