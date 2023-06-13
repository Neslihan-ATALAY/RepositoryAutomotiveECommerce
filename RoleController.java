package java.com.javahiringchallenge.controller;

import java.com.javahiringchallenge.dao.RoleDAO;
import java.com.javahiringchallenge.model.Role;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class RoleController {	
	@Autowired
	private RoleDAO roleDAO = new RoleDAO();
	
	@RequestMapping(value="/deleteRole")
	@ResponseBody
	public String deleteRole(@RequestParam(value="roleId") long id) {
		try {
			Role role = new Role();
			role = roleDAO.getRoleById(id);
			role.setDeleted(true);
			roleDAO.updateRole(role);
		} catch (Exception e) {
			return e.getMessage();
		}
		return "Role succesfully deleted!";
	}
	
	@RequestMapping(value="/createRole")
	@ResponseBody
	public String createRole(
			@RequestParam(value="roleName") String name,
			Model model) {
		try {
			Role role = new Role();
			//role.setId(id);
			role.setRoleName(name);
			role.setDeleted(false);
			roleDAO.saveRole(role);
			model.addAttribute(role);
		} catch (Exception e) {
			return e.getMessage();
		}
		return "Role succesfully created.";
	}
	
	@RequestMapping(value="/editRole")
	@ResponseBody
	public String updateRole(
			@RequestParam(value="roleId") long id,
			@RequestParam(value="roleName") String name,
			Model model) {
		try {
			Role role = new Role();
			role = roleDAO.getRoleById(id);
			if(role != null) {
				role.setId(id);
				role.setRoleName(name);
				role.setDeleted(false);
				roleDAO.updateRole(role);
				model.addAttribute(role);
			} else if (role == null) {
				return "Role is not found";
			}
		} catch (Exception e) {
			return e.getMessage();
		}
		return "Role succesfully updated.";
	}
	
	@RequestMapping(value="/listRole")
	@ResponseBody
	public List<Role> listRole() {
		try {
			return roleDAO.getRoleList();
		} catch (Exception e) {
			return null;
		}
	}
	
	@RequestMapping(value="/listRoleByUser")
	@ResponseBody
	public List<Role> listRoleByUserId(@RequestParam(value="userId") long userid) {
		try {
			return roleDAO.getRoleListByUserId(userid);
		} catch (Exception e) {
			return null;
		}
	}
	
	@RequestMapping(value="/viewRole")
	@ResponseBody
	public Role viewRole(@RequestParam(value="roleId") long id, Model model) {
		try {
			Role role = new Role();
			role = roleDAO.getRoleById(id);
			model.addAttribute(role);
			return role;
		} catch (Exception e) {
			return null;
		}
	}
}