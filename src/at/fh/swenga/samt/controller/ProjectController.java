package at.fh.swenga.samt.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.validation.ConstraintViolationException;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.TransactionSystemException;
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
		ArrayList<List<UserModel>> memberList = new ArrayList<List<UserModel>>();
		for (ProjectModel project : projects) {
			Set<Integer> memberIDs = projectRepository.findUserByPidAndActive(project.getPid());
			List<UserModel> members = new ArrayList<UserModel>();
			for (int member : memberIDs) {
				members.add(userRepository.findById(member));
			}
			memberList.add(members);
		}

		model.addAttribute("memberList", memberList);
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
		model.addAttribute("type", "archived");
		model.addAttribute("title", "All your archived projects");

		return "projects/index";
	}

	@RequestMapping("/archive")
	public String deleteDataProject(Model model, @RequestParam int id) {
		projectRepository.delete(id);

		return "forward:active/";
	}

	@RequestMapping(value = "editPage", method = RequestMethod.POST)
	@Transactional
	public String showEditForm(Model model, @RequestParam int id) {

		ProjectModel project = projectRepository.findOne(id);

		final UserDetails userdet = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		String currentUser = userdet.getUsername();
		List<UserModel> possibleMembers = userRepository.findPossibleMembers(currentUser);

		List<UserModel> previousMembers = new ArrayList();
		List<Integer> users = projectRepository.findUserByPid(project.getPid());
		for (int user : users) {
			previousMembers.add(userRepository.findById(user));
		}

		if (project != null) {
			model.addAttribute("project", project);
			model.addAttribute("previousMembers", previousMembers);
			model.addAttribute("possibleMembers", possibleMembers);
			model.addAttribute("type", "edit");
			return "projects/create";
		} else {
			model.addAttribute("errorMessage", "Couldn't find project " + id);
			return "forward:active/";
		}
	}

	@RequestMapping(value = "edit", method = RequestMethod.POST)
	@Transactional
	public String edit(@Valid @ModelAttribute ProjectModel changedProjectModel, BindingResult bindingResult,
			Model model) {

		if (bindingResult.hasErrors()) {
			String errorMessage = "";
			for (FieldError fieldError : bindingResult.getFieldErrors()) {
				errorMessage += fieldError.getField() + " is invalid<br>";
			}

			model.addAttribute("errorMessage", errorMessage);
			return "forward:editPage/";
		}

		final UserDetails userdet = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		String stringCreator = userdet.getUsername();
		List<UserModel> userList = userRepository.findByUserName(stringCreator);
		int intCreator = userList.get(0).getId();

		int pid = projectRepository.findPidById(changedProjectModel.getId());

		/*
		 * ProjectModel chp = projectRepository.findTop1ByOrderByPidDesc(); int
		 * pid = 0; if (chp == null) { pid = 1; } else { pid = chp.getPid() + 1;
		 * }
		 */

		List<Integer> previousMembers = new ArrayList<Integer>();
		List<Integer> users = projectRepository.findUserByPid(pid);
		for (int user : users) {
			previousMembers.add(userRepository.findById(user).getId());
		}

		List<Integer> newMembers = new ArrayList<Integer>();
		newMembers.add(intCreator);
		if (changedProjectModel.getUsers() != null) {
			for (UserModel user : changedProjectModel.getUsers()) {
				newMembers.add(user.getId());
			}
		}

		// List<ProjectModel> projects = projectRepository.findByPid(pid);

		List<Integer> allMembers = new ArrayList<Integer>();
		allMembers.addAll(newMembers);
		allMembers.addAll(previousMembers);

		for (int member : allMembers) {
			if (previousMembers.contains(member) && !newMembers.contains(member)) {
				ProjectModel project = projectRepository.findByUserAndPid(member, pid);
				project.setIsArchived(true);
			} else if (previousMembers.contains(member) && newMembers.contains(member)) {
				ProjectModel project = projectRepository.findByUserAndPid(member, pid);
				project.setPid(pid);
				project.setProjectName(changedProjectModel.getProjectName());
				project.setDeadline(changedProjectModel.getDeadline());
				project.setCourse(changedProjectModel.getCourse());
				project.setUser(member);
				project.setProgress(changedProjectModel.getProgress());

				if (changedProjectModel.getIsArchived() == null) {
					project.setIsArchived(false);
				} else {
					project.setIsArchived(changedProjectModel.getIsArchived());
				}
			} else if (!previousMembers.contains(member) && newMembers.contains(member)) {
				model.addAttribute(pid);
				model.addAttribute(changedProjectModel.getProjectName());
				model.addAttribute(changedProjectModel.getDeadline());
				model.addAttribute(changedProjectModel.getCourse());
				model.addAttribute(member);
				model.addAttribute(changedProjectModel.getProgress());

				ProjectModel pm = new ProjectModel(pid, changedProjectModel.getProjectName(),
						changedProjectModel.getDeadline(), changedProjectModel.getProgress(),
						changedProjectModel.getCourse(), member);
				pm.setIsArchived(false);

				projectRepository.save(pm);
			}
		}

		return "forward:active/";

	}

	@RequestMapping(value = "add", method = RequestMethod.GET)
	public String showAddProjectForm(Model model) {

		final UserDetails userdet = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		String currentUser = userdet.getUsername();
		List<UserModel> possibleMembers = userRepository.findPossibleMembers(currentUser);
		model.addAttribute("possibleMembers", possibleMembers);

		return "projects/create";
	}

	@RequestMapping(value = "add", method = RequestMethod.POST)
	@Transactional
	public String add(Model model, @RequestParam String projectName, @RequestParam String deadline,
			@RequestParam String course, @RequestParam(value = "users", required = false) Set<Integer> users) {
		{
			
			final UserDetails userdet = (UserDetails) SecurityContextHolder.getContext().getAuthentication()
					.getPrincipal();
			String stringCreator = userdet.getUsername();
			List<UserModel> userList = userRepository.findByUserName(stringCreator);
			int intCreator = userList.get(0).getId();

			Set<Integer> allMembers = new HashSet<Integer>();
			allMembers.add(intCreator);
			if (users != null) {
				allMembers.addAll(users);
			}

			SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy");
			Date formattedDeadline = new Date();
			try {
				formattedDeadline = sdf.parse(deadline);
			} catch (ParseException e) {
				model.addAttribute("errorMessage", "Date Parsing Error" + e);
			}

			ProjectModel chp = projectRepository.findTop1ByOrderByPidDesc();
			int pid = 0;
			if (chp == null) {
				pid = 1;
			} else {
				pid = chp.getPid() + 1;
			}
			try{
				
				new ProjectModel(pid, projectName, formattedDeadline, 0, course,1);
				
			}catch(Exception ex){
				System.out.println(ex.getMessage());
				String errorMessage = "test";
				model.addAttribute("errorMessage",errorMessage);
				return "projects/create";
			}
			
			
			for (int member : allMembers) {
				model.addAttribute(pid);
				model.addAttribute(projectName);
				model.addAttribute(deadline);
				model.addAttribute(course);
				model.addAttribute(member);

				ProjectModel pm = new ProjectModel(pid, projectName, formattedDeadline, 0, course, member);
				pm.setIsArchived(false);
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