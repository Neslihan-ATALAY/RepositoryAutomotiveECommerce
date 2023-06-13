package java.com.javahiringchallenge.controller;

import java.com.javahiringchallenge.dao.RoleDAO;
import java.com.javahiringchallenge.dao.UserDAO;
import java.com.javahiringchallenge.dao.UserRoleDAO;
import java.com.javahiringchallenge.model.Role;
import java.com.javahiringchallenge.model.User;
import java.com.javahiringchallenge.model.UserRole;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class UserRoleController {
	@Autowired
	private UserRoleDAO userRoleDAO = new UserRoleDAO();
	private UserDAO userDAO = new UserDAO();
	private RoleDAO roleDAO = new RoleDAO();
	
	@RequestMapping(value="/deleteUserRole")
	@ResponseBody
	public String deleteUserRole(@RequestParam(value="userRoleId") long id) {
		try {
			UserRole userRole = new UserRole();
			userRole = userRoleDAO.getUserRoleById(id);
			userRole.setDeleted(true);
			userRoleDAO.updateUserRole(userRole);
		} catch (Exception e) {
			return e.getMessage();
		}
		return "User's role is succesfully deleted!";
	}
	
	@RequestMapping(value="/createUserRole")
	@ResponseBody
	public String createUserRole(
			@RequestParam(value="userId") long userid, 
			@RequestParam(value="roleId") long roleid,
			Model model) {
		try {
			UserRole userRole = new UserRole();
			Role role = new Role();
			User user = new User();
			userRole.setUserid(userid);
			user = userDAO.getUserById(userid);
			if(user != null) {
				userRole.setUser(user);
			}			
			userRole.setRoleid(roleid);			
			role = roleDAO.getRoleById(roleid);
			if (role != null) {
				userRole.setRole(role);
			}
			userRole.setDeleted(false);
			userRole.setChecked(false);
			userRoleDAO.saveUserRole(userRole);
			model.addAttribute(model);
		} catch (Exception e) {
			return e.getMessage();
		}
		return "User succesfully created.";
	}
	
	@RequestMapping(value="/editUserRole")
	@ResponseBody
	public String updateUserRole(
			@RequestParam(value="userRoleId") long id,
			@RequestParam(value="userId") long userid, 
			@RequestParam(value="roleId") long roleid,
			Model model) {
		try {
			UserRole userRole = new UserRole();
			Role role = new Role();
			User user = new User();
			userRole = userRoleDAO.getUserRoleById(id);
			if(userRole != null) {
				userRole.setUserid(userid);
				user = userDAO.getUserById(userid);
				if(user != null) {
					userRole.setUser(user);
				}			
				userRole.setRoleid(roleid);			
				role = roleDAO.getRoleById(roleid);
				if (role != null) {
					userRole.setRole(role);
				}
				userRole.setDeleted(false);
				userRole.setChecked(false);
				userRoleDAO.updateUserRole(userRole);
				model.addAttribute(userRole);
			} else if (userRole == null) {
				return "User role is not found.";
			}
		} catch (Exception e) {
			return e.getMessage();
		}
		return "User's role is succesfully updated!";
	}
	
	@RequestMapping(value="/listUserRole")
	@ResponseBody
	public List<UserRole> listUserRole() {
		try {
			return userRoleDAO.getUserRoleList();
		} catch (Exception e) {
			return null;
		}
	}
	
	@RequestMapping(value="/viewUserRole")
	@ResponseBody
	public UserRole viewUserRole(@RequestParam(value="userRoleId") long id,
			Model model) {
		try {
			UserRole userRole = new UserRole();
			userRole = userRoleDAO.getUserRoleById(id);
			model.addAttribute(userRole);
			return userRole;
		} catch (Exception e) {
			return null;
		}
	}
	
	@RequestMapping(value="/viewByUserAndRole")
	@ResponseBody
	public UserRole viewUserRoleByUserIdAndRoleId(
			@RequestParam(value="userId") long userid, 
			@RequestParam(value="roleId") long roleid,
			Model model) {
		try {
			UserRole userRole = new UserRole();
			userRole = userRoleDAO.getUserRoleByUserIdAndRoleId(userid, roleid);
			model.addAttribute(userRole);
			return userRole;
		} catch (Exception e) {
			return null;
		}
	}
}