package at.fh.swenga.samt.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Set;

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

import at.fh.swenga.samt.dao.ProjectRepository;
import at.fh.swenga.samt.dao.UserRepository;
import at.fh.swenga.samt.model.ProjectModel;
import at.fh.swenga.samt.model.UserModel;

@Controller
@RequestMapping("/projects")
public class ProjectController {

	@Autowired
	ProjectRepository projectRepository;

	@Autowired
	UserRepository userRepository;

	@RequestMapping(value = { "/", "active/" })
	public String indexProjects(Model model) {

		final UserDetails userdet = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		String userName = userdet.getUsername();

		List<UserModel> userList = userRepository.findByUserName(userName);
		int user = userList.get(0).getId();

		List<ProjectModel> projects = projectRepository.findActiveProjects(user);

		model.addAttribute("projects", projects);
		model.addAttribute("type", "findActiveProjects");
		model.addAttribute("title", "All your projects");

		return "projects/index";
	}

	@RequestMapping(value = { "/", "archived/" })
	public String indexProjectsArchived(Model model) {

		final UserDetails userdet = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		String userName = userdet.getUsername();

		List<UserModel> userList = userRepository.findByUserName(userName);
		int user = userList.get(0).getId();

		List<ProjectModel> projects = projectRepository.findArchivedProjects(user);

		model.addAttribute("projects", projects);
		model.addAttribute("type", "findArchivedProjects");
		model.addAttribute("title", "All your archived projects");

		return "projects/index";
	}

	@RequestMapping("/archive")
	public String deleteDataProject(Model model, @RequestParam int id) {
		projectRepository.delete(id);

		return "forward:active/";
	}

	@RequestMapping(value = "/edit", method = RequestMethod.GET)
	@Transactional
	public String showEditForm(Model model, @RequestParam int id) {

		ProjectModel project = projectRepository.findOne(id);

		if (project != null) {
			model.addAttribute("project", project);

			return "notes/create";
		} else {
			model.addAttribute("errorMessage", "Couldn't find project " + id);
			return "forward:active/";
		}

	}

	@RequestMapping(value = "/edit", method = RequestMethod.POST)
	@Transactional
	public String edit(@Valid @ModelAttribute ProjectModel changedProjectModel, BindingResult bindingResult,
			Model model) {

		if (bindingResult.hasErrors()) {
			String errorMessage = "";
			for (FieldError fieldError : bindingResult.getFieldErrors()) {
				errorMessage += fieldError.getField() + " is invalid<br>";
			}

			model.addAttribute("errorMessage", errorMessage);
			return "forward:active/";
		}

		ProjectModel project = projectRepository.findOne(changedProjectModel.getUid());

		if (project == null) {
			model.addAttribute("errorMessage", "Project does not exist!<br>");
		} else {

			project.setProjectName(changedProjectModel.getProjectName());
			project.setDeadline(changedProjectModel.getDeadline());
			project.setProgress(changedProjectModel.getProgress());
			project.setCourse(changedProjectModel.getCourse());
			project.setIsArchived(changedProjectModel.getIsArchived());

		}

		return "forward:active/";
	}

	@RequestMapping(value = "add", method = RequestMethod.GET)
	public String showAddProjectForm(Model model) {
		
		final UserDetails userdet = (UserDetails) SecurityContextHolder.getContext().getAuthentication()
				.getPrincipal();
		String currentUser = userdet.getUsername();
		List<UserModel> possibleMembers = userRepository.findPossibleMembers(currentUser);
		model.addAttribute("users", possibleMembers);

		return "projects/create";
	}

	@RequestMapping(value = "add", method = RequestMethod.POST)
	@Transactional
	public String add(Model model, @RequestParam String projectName, @RequestParam String deadline,
			@RequestParam String course, @RequestParam Set<Integer> participants) {
		{
			final UserDetails userdet = (UserDetails) SecurityContextHolder.getContext().getAuthentication()
					.getPrincipal();
			String stringCreator = userdet.getUsername();
			List<UserModel> userList = userRepository.findByUserName(stringCreator);
			int intCreator = userList.get(0).getId();

			participants.add(intCreator);

			SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy");
			Date formattedDeadline = new Date();
			try {
				formattedDeadline = sdf.parse(deadline);
			} catch (ParseException e) {
				model.addAttribute("errorMessage", "Date Parsing Error" + e);
			}

			for (int user : participants) {
				model.addAttribute(projectName);
				model.addAttribute(deadline);
				model.addAttribute(course);
				model.addAttribute(user);

				ProjectModel pm = new ProjectModel(projectName, formattedDeadline, 0, course, user, false);
				
				projectRepository.save(pm);

			}

		}

		return "forward:active/";
	}

	@ExceptionHandler(Exception.class)
	public String handleAllException(Exception ex) {

		return "showError";

	}
}
