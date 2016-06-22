package at.fh.swenga.samt.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import at.fh.swenga.samt.dao.ProjectRepository;
import at.fh.swenga.samt.dao.UserRepository;
import at.fh.swenga.samt.model.ProjectModel;
import at.fh.swenga.samt.model.UserModel;

@Controller
public class ReportController {
	@Autowired
	UserRepository userRepository;
	@Autowired
	ProjectRepository projectRepository;

	@RequestMapping(value = { "projects/report" })
	public String report(Model model, @RequestParam(required = false) String excel,
			@RequestParam(name = "userId", required = false) List<Integer> userIds) {

		final UserDetails userdet = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		String userName = userdet.getUsername();
		List<UserModel> userList = userRepository.findByUserName(userName);
		int user = userList.get(0).getId();

		List<ProjectModel> projects = projectRepository.findActiveProjects(user);
		model.addAttribute("projects", projects);

		return "excelReport";

	}

	@ExceptionHandler(Exception.class)
	public String handleAllException(Exception ex) {
		return "showError";

	}

}
