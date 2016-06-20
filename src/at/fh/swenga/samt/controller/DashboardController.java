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
		
		List<UserModel> user = userRepository.findByUserName(userName);
		UserModel userModel = user.get(0);
		
		//User
		
		List<UserModel> users = userRepository.findByUserName(userName);
		model.addAttribute("user", users);
		
		
		//Homework
		
		List<HomeworkModel> homeworks = homeworkRepository.findByUser(userModel);

		model.addAttribute("homeworks", homeworks);
		model.addAttribute("titleHomework", "Homework");
		
		//Projects
		
		List<UserModel> userList = userRepository.findByUserName(userName);
		int intUser = userList.get(0).getId();

		List<ProjectModel> projects = projectRepository.findActiveProjects(intUser);
		ArrayList<List<UserModel>> memberList = new ArrayList<List<UserModel>>();
		for (ProjectModel project : projects) {
			Set<Integer> memberIDs = projectRepository.findUserByPidAndActive(project.getPid());
			List<UserModel> members = new ArrayList<UserModel>();
			for (int member : memberIDs) {
				members.add(userRepository.findById(member));
			}
			memberList.add(members);
		}
		
		model.addAttribute("projects", projects);
		model.addAttribute("titleProjects", "Projects");
		
		//Forum
		
		List<ForumModel> forum = forumRepository.findTop1ByOrderByIdDesc();
		List<UserModel> creator = new ArrayList<UserModel>();
		for (ForumModel post : forum) {
			creator.add(userRepository.findById(post.getUser()));
		}

		model.addAttribute("titleForum", "Most recent post");
		model.addAttribute("creator", creator);
		model.addAttribute("posts", forum);
		
		model.addAttribute("location", "dashboard");
		
		return "index";
	}
	
	@ExceptionHandler(Exception.class)
	public String handleAllException(Exception ex) {

		return "showError";

	}
	
}
