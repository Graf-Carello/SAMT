package at.fh.swenga.samt.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import at.fh.swenga.samt.dao.UserRepository;
import at.fh.swenga.samt.model.UserModel;

@Controller
@RequestMapping("/admin")
public class UserController {

	@Autowired
	UserRepository userRepository;

	@RequestMapping(value = { "/", "admin/" })
	public String list(Model model) {
		List<UserModel> users = userRepository.findAll();
		model.addAttribute("users", users);
		model.addAttribute("type", "findAll");
		return "admin/admin";
	}

	@RequestMapping("/delete")
	public String deleteData(Model model, @RequestParam int id) {
		userRepository.delete(id);

		return "forward:/";
	}

	@RequestMapping(value = "edit", method = RequestMethod.GET)
	@Transactional
	public String showEditForm(Model model, @RequestParam int id) {

		UserModel user = userRepository.findOne(id);

		if (user != null) {
			model.addAttribute("user", user);

			return "admin/create";
		} else {
			model.addAttribute("errorMessage", "Couldn't find user " + id);
			return "forward:admin/";
		}

	}

	@RequestMapping(value = "edit", method = RequestMethod.POST)
	@Transactional
	public String edit(@Valid @ModelAttribute UserModel changedUserModel, BindingResult bindingResult, Model model) {

		if (bindingResult.hasErrors()) {
			String errorMessage = "";
			for (FieldError fieldError : bindingResult.getFieldErrors()) {
				errorMessage += fieldError.getField() + " is invalid<br>";
			}

			model.addAttribute("errorMessage", errorMessage);
			return "forward:admin/";
		}

		UserModel user = userRepository.findOne(changedUserModel.getId());

		if (user == null) {
			model.addAttribute("errorMessage", "User does not exist!<br>");
		} else {
			user.setUserName(changedUserModel.getUserName());
			user.setFirstName(changedUserModel.getFirstName());
			user.setLastName(changedUserModel.getLastName());
			user.setDegreeCourse(changedUserModel.getDegreeCourse());
			user.setEmail(changedUserModel.getEmail());
			user.setPassword(changedUserModel.getPassword());
			user.setProfilePicture(changedUserModel.getProfilePicture());
		}

		return "forward:admin/";
	}

	@RequestMapping(value = "add", method = RequestMethod.GET)
	public String showAddNoteForm(Model model) {

		return "admin/create";
	}

	@RequestMapping(value = "add", method = RequestMethod.POST)
	@Transactional
	public String add(Model model, @RequestParam String userName, @RequestParam String firstName,
			@RequestParam String lastName, @RequestParam String degreeCourse, @RequestParam String email,
			@RequestParam String password, @RequestParam String profilePicture) {
		{
			final UserDetails userdet = (UserDetails) SecurityContextHolder.getContext().getAuthentication()
					.getPrincipal();
			String stringCreator = userdet.getUsername();

			List<UserModel> userList = userRepository.findByUserName(stringCreator);
			int intCreator = userList.get(0).getId();

			model.addAttribute(userName);
			model.addAttribute(firstName);
			model.addAttribute(lastName);
			model.addAttribute(degreeCourse);
			model.addAttribute(email);
			model.addAttribute(password);
			model.addAttribute(profilePicture);

			UserModel um = new UserModel(userName, firstName, lastName, degreeCourse, email, password, profilePicture);

			userRepository.save(um);
		}

		return "forward:admin/";
	}

	@ExceptionHandler(Exception.class)
	public String handleAllException(Exception ex) {

		return "showError";

	}
}