package at.fh.swenga.samt.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import at.fh.swenga.samt.dao.UserRepository;
import at.fh.swenga.samt.model.UserModel;

@Controller
public class UserController {

	@Autowired
	UserRepository userRepository;

	@RequestMapping(value = { "/", "list" })
	public String index(Model model) {
		List<UserModel> users = userRepository.findAll();
		model.addAttribute("users", users);
		model.addAttribute("type", "findAll");
		return "index";
	}
	
	@RequestMapping("users")
	public String users(Model model) {
		List<UserModel> users = userRepository.findAll();
		model.addAttribute("users", users);
		model.addAttribute("type", "findAll");
		return "users";
	}

	@RequestMapping(value = { "/getPage" })
	public String getPage(Pageable page, Model model) {

		Page<UserModel> users = userRepository.findAll(page);
		model.addAttribute("users", users.getContent());
		model.addAttribute("usersPage", users);

		return "index";
	}

	@RequestMapping(value = { "/find" })
	public String find(Model model, @RequestParam String searchString, @ModelAttribute("type") String type) {
		List<UserModel> users = null;
		int count = 0;

		switch (type) {
		case "findAll":
			users = userRepository.findAll();
			break;

		default:
			users = userRepository.findAll();
		}

		model.addAttribute("users", users);
		model.addAttribute("count", count);
		return "index";
	}

	@RequestMapping(value = { "/findById" })
	public String findById(@RequestParam("id") UserModel u, Model model) {
		List<UserModel> users = new ArrayList<>();
		users.add(u);
		model.addAttribute("users", users);

		return "index";
	}

	@RequestMapping("/fill")
	@Transactional
	public String fillData(Model model) {

		UserModel um1 = new UserModel(1, "Max", "Mustermann", "IMA", "max@mustermann.at", "password", "user1.png");
		UserModel um2 = new UserModel(2, "Michael", "Michel", "IMA", "michel@gmail.com", "password", "user1.png");
		UserModel um3 = new UserModel(3, "Moritz", "More", "IMA", "m@more.at", "password", "user1.png");

		userRepository.save(um1);
		userRepository.save(um2);
		userRepository.save(um3);

		return "forward:list";
	}

	@RequestMapping("/delete")
	public String deleteData(Model model, @RequestParam int id) {
		userRepository.delete(id);

		return "forward:list";
	}

	// @ExceptionHandler(Exception.class)
	public String handleAllException(Exception ex) {

		return "showError";

	}
}