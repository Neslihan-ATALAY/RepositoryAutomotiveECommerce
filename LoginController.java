package java.com.javahiringchallenge.controller;

import java.com.javahiringchallenge.dao.*;
import java.com.javahiringchallenge.model.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class LoginController {
	private UserDAO userDAO = new UserDAO();
	private ProductDAO productDAO = new ProductDAO();
	
	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String init(Model model) {
		model.addAttribute("msg", "Please Enter Your Login Details");
		return "login";
	}
	
	@RequestMapping(value = "/login", method = RequestMethod.POST)
	@ResponseBody
	public String submit(
			@RequestParam(value="userName") String userName, 
			@RequestParam(value="password") String password,
			Model modelList, @ModelAttribute("user") User user) {		
		User User = null;
		if ((userName != null || userName != "") && (password != null || password != "")) {
			User = new User();
			User = userDAO.getUserByUserName(userName);
			if (User != null && (User.getUserName() != "" && User.getUserName() != null)
					&& (User.getPassword() != "" && User.getPassword() != null)) {
				if (User.getUserName().equals(userName) && User.getPassword().equals(password)) {
					modelList.addAttribute("userid", User.getId());
					modelList.addAttribute("nameandusername", User.getName() + "(" + User.getUserName() + ")");
					modelList.addAttribute("username", User.getUserName());
					modelList.addAttribute("password", User.getPassword());
					modelList.addAttribute("name", User.getName());
					modelList.addAttribute("surname", User.getSurname());
					modelList = (Model) productDAO.getProductList();
					user = User;
					return "index";
				} else {
					modelList.addAttribute("error", "Invalid Username and/or Password");
					return "login";
				}
			} else if (User == null) {
				modelList.addAttribute("error", "Sorry, Invalid Username and/or Password. " +
						"Username will be Checked or could be Deleted.");
				return "login";
			}
		} else {
			modelList.addAttribute("error", "Please, Enter Username and/or Password Again");
			return "login";
		}
		return "login";
	}
}