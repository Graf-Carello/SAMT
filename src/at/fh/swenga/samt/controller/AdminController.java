package at.fh.swenga.samt.controller;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javax.servlet.ServletContext;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
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
import at.fh.swenga.samt.dao.UserRoleRepository;
import at.fh.swenga.samt.model.UserModel;
import at.fh.swenga.samt.model.UserRole;

@Controller
@RequestMapping("/admin")
public class AdminController {

	@Autowired
	UserRepository userRepository;
	@Autowired
	UserRoleRepository userRoleRepository;
	@Autowired
    ServletContext context; 

	private BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(4);

	@RequestMapping(value = { "/", "index" })
	public String list(Model model) {
		List<UserModel> users = userRepository.findAll();
		model.addAttribute("users", users);
		model.addAttribute("type", "findAll");
		return "admin/index";
	}

	@RequestMapping("/disable")
	@Transactional
	public String disableUser(Model model, @RequestParam int id) {
		UserModel user = userRepository.findOne(id);
		user.setEnabled(false);

		return "forward:index/";
	}

	@RequestMapping("/enable")
	@Transactional
	public String enableUser(Model model, @RequestParam int id) {
		UserModel user = userRepository.findOne(id);
		user.setEnabled(true);

		return "forward:index/";
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
			return "forward:index/";
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
			return "forward:edit/";
		}

		UserModel user = userRepository.findOne(changedUserModel.getId());

		if (user == null) {
			model.addAttribute("errorMessage", "User does not exist!<br>");
		} else {

			// String sanitizedUserName =
			// SanitizeString.setAsText(changedUserModel.getUserName());

			user.setUserName(changedUserModel.getUserName());
			user.setFirstName(changedUserModel.getFirstName());
			user.setLastName(changedUserModel.getLastName());
			user.setDegreeCourse(changedUserModel.getDegreeCourse());
			user.setEmail(changedUserModel.getEmail());
			user.setPassword(changedUserModel.getPassword());
			user.setProfilePicture(changedUserModel.getProfilePicture());
		}

		return "forward:index/";
	}

	@RequestMapping(value = "add", method = RequestMethod.GET)
	public String showAddNoteForm(Model model) {

		return "admin/create";
	}

	@RequestMapping(value = "add", method = RequestMethod.POST)
	@Transactional
	public String add(Model model, @RequestParam String userName, @RequestParam String firstName,
			@RequestParam String lastName, @RequestParam String degreeCourse, @RequestParam String email,
			@RequestParam String password) {
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

			password = encoder.encode(password);
			model.addAttribute(password);

			List<String> profilepics = new ArrayList<String>() {{ 
				add("anna.png");
				add("felix.png");
				add("flower.png");
				add("gamefolder.png");
				add("hans.png");
				add("ironman.png");
				add("mateshka.png");
				add("michael.png");
				add("movie.png");
				add("paul.png");
				add("redgirl.png");
				add("warhol.png");
				add("wrench.png");
			}};
			
			Random randomGenerator = new Random();
			int random = randomGenerator.nextInt(profilepics.size());
	        String randompic = profilepics.get(random);
			model.addAttribute(randompic);

			UserModel um = new UserModel(userName, firstName, lastName, degreeCourse, email, password, randompic);

			um.setEnabled(true);

			userRepository.save(um);

			String role = "ROLE_USER";
			UserRole ur = new UserRole(role, um);

			userRoleRepository.save(ur);
		}

		return "forward:index/";
	}

	@ExceptionHandler(Exception.class)
	public String handleAllException(Exception ex) {

		return "showError";

	}
}