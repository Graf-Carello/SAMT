package at.fh.swenga.samt.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;

import at.fh.swenga.samt.dao.ForumRepository;
import at.fh.swenga.samt.dao.HomeworkRepository;
import at.fh.swenga.samt.dao.ProjectRepository;
import at.fh.swenga.samt.dao.UserRepository;
import at.fh.swenga.samt.model.ForumModel;
import at.fh.swenga.samt.model.HomeworkModel;
import at.fh.swenga.samt.model.ProjectModel;
import at.fh.swenga.samt.model.UserModel;


@Controller
@RequestMapping("/")
public class DashboardController {

	@Autowired
	UserRepository userRepository;
	
	@Autowired
	HomeworkRepository homeworkRepository;
	
	@Autowired
	ProjectRepository projectRepository;
	
	@Autowired
	ForumRepository forumRepository;

	@RequestMapping(value = { "/","index" })
	public String dashboard(Model model) {
		
		final UserDetails userdet = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		String userName = userdet.getUsername();
		
		List<UserModel> userList = userRepository.findByUserName(userName);
		UserModel user = userList.get(0);
		int user_id = user.getId();
		model.addAttribute("user", user);
		
		//Homework
		
		HomeworkModel homework = homeworkRepository.findByUserOrderByDeadlineAsc(user).get(0);
		model.addAttribute("homework", homework);
		
		//Projects

		ProjectModel project = projectRepository.findLatestFromUser(user_id).get(0);
		model.addAttribute("project", project);
		
		//Forum
		
		ForumModel post = forumRepository.findTop1ByOrderByIdDesc().get(0);
		UserModel creator = userRepository.findById(post.getUser());

		model.addAttribute("creator", creator);
		model.addAttribute("post", post);
		
		model.addAttribute("location", "dashboard");
		
		return "index";
	}
	
	@ExceptionHandler(Exception.class)
	public String handleAllException(Exception ex) {

		return "showError";

	}
	
}
