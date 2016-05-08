package at.fh.swenga.samt.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import at.fh.swenga.samt.model.UserManager;
import at.fh.swenga.samt.model.UserModel;

@Controller
public class UserController {
	@Autowired
	private UserManager userManager;

	@RequestMapping(value = { "/", "listUser" })
	public String showAllUsers(Model model) {
		model.addAttribute("users", userManager.getAllUsers());
		return "index";
	}

	@RequestMapping(value = {"/fillUserList", "/fill"})
	public String fillUserList() {

		userManager.addUser(userManager.createUserModel(1, "Max", "Mustermann", "IMA", "max@mustermann.at", "password",
				"user1.png"));

		return "forward:/listUser";
	}

	@RequestMapping("/deleteUser")
	public String delete(Model model, @RequestParam int id) {
		boolean isRemoved = userManager.remove(id);

		if (isRemoved) {
			model.addAttribute("warningMessage", "User " + id + " deleted");
		} else {
			model.addAttribute("errorMessage", "There is no User " + id);
		}

		// Multiple ways to "forward" to another Method
		// return "forward:/listUser";
		return showAllUsers(model);
	}

	@RequestMapping("/searchUsers")
	public String search(Model model, @RequestParam String searchString) {
		model.addAttribute("users", userManager.getFilteredUsers(searchString));
		return "listUser";
	}

	@RequestMapping(value = "/addUser", method = RequestMethod.GET)
	public String showAddUserForm(Model model) {
		return "editUser";
	}

	@RequestMapping(value = "/addUser", method = RequestMethod.POST)
	public String addUser(@Valid @ModelAttribute UserModel newUserModel, BindingResult bindingResult, Model model) {

		if (bindingResult.hasErrors()) {
			String errorMessage = "";
			for (FieldError fieldError : bindingResult.getFieldErrors()) {
				errorMessage += fieldError.getField() + " is invalid<br>";
			}
			model.addAttribute("errorMessage", errorMessage);
			return "forward:/listUser";
		}

		UserModel user = userManager.getUserById(newUserModel.getId());

		if (user != null) {
			model.addAttribute("errorMessage", "User already exists!<br>");
		} else {
			userManager.addUser(newUserModel);
			model.addAttribute("message", "New user " + newUserModel.getId() + " added.");
		}

		return "forward:/listUser";
	}

	@RequestMapping(value = "/changeUser", method = RequestMethod.GET)
	public String showChangeUserForm(Model model, @RequestParam int id) {
		UserModel user = userManager.getUserById(id);
		if (user != null) {
			model.addAttribute("user", user);
			return "editUser";
		} else {
			model.addAttribute("errorMessage", "Couldn't find user " + id);
			return "forward:/listUser";
		}
	}

	@RequestMapping(value = "/changeUser", method = RequestMethod.POST)
	public String changeUser(@Valid @ModelAttribute UserModel changedUserModel, BindingResult bindingResult,
			Model model) {

		if (bindingResult.hasErrors()) {
			String errorMessage = "";
			for (FieldError fieldError : bindingResult.getFieldErrors()) {
				errorMessage += fieldError.getField() + " is invalid<br>";
			}
			model.addAttribute("errorMessage", errorMessage);
			return "forward:/listUser";
		}

		UserModel user = userManager.getUserById(changedUserModel.getId());

		if (user == null) {
			model.addAttribute("errorMessage", "User does not exist!<br>");
		} else {
			user.setId(changedUserModel.getId());
			user.setFirstName(changedUserModel.getFirstName());
			user.setLastName(changedUserModel.getLastName());
			user.setDegreeCourse(changedUserModel.getDegreeCourse());

			model.addAttribute("message", "Changed user " + changedUserModel.getId());
		}

		return "forward:/listUser";
	}

	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String handleLogin() {
		return "login";
	}

	@ExceptionHandler(Exception.class)
	public String handleAllException(Exception ex) {

		return "showError";

	}
}
