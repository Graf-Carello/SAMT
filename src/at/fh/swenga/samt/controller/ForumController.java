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

import at.fh.swenga.samt.dao.ForumRepository;
import at.fh.swenga.samt.dao.UserRepository;
import at.fh.swenga.samt.model.ForumModel;
import at.fh.swenga.samt.model.UserModel;

@Controller
@RequestMapping("/forum")
public class ForumController {

	@Autowired
	ForumRepository forumRepository;

	@Autowired
	UserRepository userRepository;

	@RequestMapping(value = { "/", "index/" })
	public String index(Model model) {
		List<ForumModel> forum = forumRepository.findAll();
		model.addAttribute("forum", forum);
		model.addAttribute("type", "findAll");
		return "forum/index";
	}

	@RequestMapping("/delete")
	public String deleteData(Model model, @RequestParam int id) {
		forumRepository.delete(id);

		return "forward:index/";
	}

	@RequestMapping(value = "edit", method = RequestMethod.GET)
	@Transactional
	public String showEditForm(Model model, @RequestParam int id) {

		ForumModel forum = forumRepository.findOne(id);

		if (forum != null) {
			model.addAttribute("forum", forum);

			return "forum/create";
		} else {
			model.addAttribute("errorMessage", "Couldn't find forum " + id);
			return "forward:index/";
		}

	}

	@RequestMapping(value = "edit", method = RequestMethod.POST)
	@Transactional
	public String edit(@Valid @ModelAttribute ForumModel changedForumModel, BindingResult bindingResult, Model model) {

		if (bindingResult.hasErrors()) {
			String errorMessage = "";
			for (FieldError fieldError : bindingResult.getFieldErrors()) {
				errorMessage += fieldError.getField() + " is invalid<br>";
			}

			model.addAttribute("errorMessage", errorMessage);
			return "forward:index/";
		}

		ForumModel forum = forumRepository.findOne(changedForumModel.getId());

		if (forum == null) {
			model.addAttribute("errorMessage", "Forum does not exist!<br>");
		} else {

			forum.setForumName(changedForumModel.getForumName());
			forum.setPost(changedForumModel.getPost());
			forum.setUser(changedForumModel.getUser());
		}

		return "forward:index/";
	}

	@RequestMapping(value = "add", method = RequestMethod.GET)
	public String showAddNoteForm(Model model) {

		return "forum/create";
	}

	@RequestMapping(value = "add", method = RequestMethod.POST)
	@Transactional
	public String add(Model model, @RequestParam String title, @RequestParam String content) {
		{
			final UserDetails userdet = (UserDetails) SecurityContextHolder.getContext().getAuthentication()
					.getPrincipal();
			String stringCreator = userdet.getUsername();

			List<UserModel> userList = userRepository.findByUserName(stringCreator);
			int intCreator = userList.get(0).getId();

			model.addAttribute(title);
			model.addAttribute(content);
			model.addAttribute(intCreator);

			ForumModel fm = new ForumModel(title, content, intCreator);

			forumRepository.save(fm);
		}

		return "forward:index/";
	}

	@ExceptionHandler(Exception.class)
	public String handleAllException(Exception ex) {

		return "showError";

	}
}
