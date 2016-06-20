package at.fh.swenga.samt.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
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

import at.fh.swenga.samt.dao.HomeworkRepository;
import at.fh.swenga.samt.dao.UserRepository;
import at.fh.swenga.samt.model.HomeworkModel;
import at.fh.swenga.samt.model.UserModel;

@Controller
@RequestMapping("/homework")
public class HomeworkController {

	@Autowired
	HomeworkRepository homeworkRepository;

	@Autowired
	UserRepository userRepository;

	@RequestMapping(value = { "/", "index/" })
	public String index(Model model) {
		List<HomeworkModel> homeworks = homeworkRepository.findAll();

		model.addAttribute("homeworks", homeworks);
		model.addAttribute("title", "Homework");

		return "homework/index";
	}

	@RequestMapping("/delete")
	public String deleteData(Model model, @RequestParam int id) {
		homeworkRepository.delete(id);

		return "forward:index/";
	}

	@RequestMapping(value = "edit", method = RequestMethod.GET)
	@Transactional
	public String showEditForm(Model model, @RequestParam int id) {

		HomeworkModel homework = homeworkRepository.findOne(id);

		if (homework != null) {
			model.addAttribute("homework", homework);

			return "homework/create";
		} else {
			model.addAttribute("errorMessage", "Couldn't find homework " + id);
			return "forward:index/";
		}

	}

	@RequestMapping(value = "edit", method = RequestMethod.POST)
	@Transactional
	public String edit(@Valid @ModelAttribute HomeworkModel changedHomeworkModel, BindingResult bindingResult,
			Model model) {

		if (bindingResult.hasErrors()) {
			String errorMessage = "";
			for (FieldError fieldError : bindingResult.getFieldErrors()) {
				errorMessage += fieldError.getField() + " is invalid<br>";
			}
			model.addAttribute("errorMessage", errorMessage);
			return "forward:edit";
		}

		HomeworkModel homework = homeworkRepository.findOne(changedHomeworkModel.getId());
		
		if (homework == null) {
			model.addAttribute("errorMessage", "Forum does not exist!<br>");
		} else {

			homework.setCourse(changedHomeworkModel.getCourse());
			homework.setDescription(changedHomeworkModel.getDescription());
			homework.setDeadline(changedHomeworkModel.getDeadline());
			
		}
		return "forward:index/";
	}

	@RequestMapping(value = "add", method = RequestMethod.GET)
	public String showAddNoteForm(Model model) {

		return "homework/create";
	}

	@RequestMapping(value = "add", method = RequestMethod.POST)
	@Transactional
	public String add(Model model, @RequestParam String course, @RequestParam String description,
			@RequestParam String deadline) {
		{
			final UserDetails userdet = (UserDetails) SecurityContextHolder.getContext().getAuthentication()
					.getPrincipal();
			String stringOwner = userdet.getUsername();

			List<UserModel> userList = userRepository.findByUserName(stringOwner);
			int intOwner = userList.get(0).getId();
			
			SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy");
			Date formattedDeadline = new Date();
			try {
				formattedDeadline = sdf.parse(deadline);
			} catch (ParseException e) {
				model.addAttribute("errorMessage", "Date Parsing Error" + e);
			}

			model.addAttribute(course);
			model.addAttribute(description);
			model.addAttribute(formattedDeadline);
			model.addAttribute(intOwner);

			HomeworkModel hm = new HomeworkModel(course, description, formattedDeadline);

			homeworkRepository.save(hm);
		}
		return "forward:index/";
	}

	@ExceptionHandler(Exception.class)
	public String handleAllException(Exception ex) {

		return "showError";
	}
}
