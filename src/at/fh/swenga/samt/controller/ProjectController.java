package at.fh.swenga.samt.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import at.fh.swenga.samt.dao.ProjectRepository;
import at.fh.swenga.samt.model.ProjectModel;

@Controller
@RequestMapping("/projects")
public class ProjectController {

	@Autowired
	ProjectRepository projectRepository;

	@RequestMapping("")
	public String index(Model model) {
		List<ProjectModel> project = projectRepository.findAll();
		model.addAttribute("project", project);
		model.addAttribute("type", "findAll");
		return "index";
	}

	@RequestMapping(value = { "/getPage" })
	public String getPage(Pageable page, Model model) {

		Page<ProjectModel> project = projectRepository.findAll(page);
		model.addAttribute("project", project.getContent());
		model.addAttribute("usersPage", project);

		return "index";
	}

	@RequestMapping(value = { "/find" })
	public String find(Model model, @RequestParam String searchString, @ModelAttribute("type") String type) {
		List<ProjectModel> project = null;
		int count = 0;

		switch (type) {
		case "findAll":
			project = projectRepository.findAll();
			break;

		default:
			project = projectRepository.findAll();
		}

		model.addAttribute("project", project);
		model.addAttribute("count", count);
		return "index";
	}

	@RequestMapping(value = { "/findById" })
	public String findById(@RequestParam("id") ProjectModel p, Model model) {
		List<ProjectModel> project = new ArrayList<>();
		project.add(p);
		model.addAttribute("project", project);

		return "index";
	}

	@RequestMapping("/fill")
	@Transactional
	public String fillData(Model model) {

		ProjectModel pm1 = new ProjectModel("Project SAMT", new Date(), "25%",  "SWENGA");
		ProjectModel pm2 = new ProjectModel("Project Game of Jumps", new Date(), "10%",  "DMT");
		ProjectModel pm3 = new ProjectModel("Passwords term paper", new Date(), "0%",  "HVSYS");
		

		projectRepository.save(pm1);
		projectRepository.save(pm2);
		projectRepository.save(pm3);
		
		return "forward:list";
	}

	@RequestMapping("/delete")
	public String deleteData(Model model, @RequestParam int id) {
		projectRepository.delete(id);

		return "forward:list";
	}

	@ExceptionHandler(Exception.class)
	public String handleAllException(Exception ex) {

		return "showError";

	}
}
