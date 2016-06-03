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

	@RequestMapping(value = {"","list"})
	public String indexProjects(Model model) {
		List<ProjectModel> projects = projectRepository.findAll();
		model.addAttribute("projects", projects);
		model.addAttribute("type", "findAllProjects");
		return "projects";
	}

	@RequestMapping(value = { "/getPage" })
	public String getPageProjects(Pageable page, Model model) {

		Page<ProjectModel> projects = projectRepository.findAll(page);
		model.addAttribute("projects", projects.getContent());
		model.addAttribute("usersPage", projects);

		return "projects";
	}

	@RequestMapping(value = { "/find" })
	public String findProject(Model model, @RequestParam String searchString, @ModelAttribute("type") String type) {
		List<ProjectModel> projects = null;
		int count = 0;

		switch (type) {
		case "findAll":
			projects = projectRepository.findAll();
			break;

		default:
			projects = projectRepository.findAll();
		}

		model.addAttribute("projects", projects);
		model.addAttribute("count", count);
		return "projects";
	}

	@RequestMapping(value = { "/findById" })
	public String findByIdProject(@RequestParam("id") ProjectModel g, Model model) {
		List<ProjectModel> projects = new ArrayList<>();
		projects.add(g);
		model.addAttribute("projects", projects);

		return "projects";
	}

	  @RequestMapping("/fill")
	  @Transactional
	  public String fillData(Model model) {

	    ProjectModel pm1 = new ProjectModel("Project SAMT", new Date(), "25",  "SWENGA", "1");
	    ProjectModel pm2 = new ProjectModel("Project Game of Jumps", new Date(), "10",  "DMT", "1");
	    ProjectModel pm3 = new ProjectModel("Passwords term paper", new Date(), "0",  "HVSYS", "2");
	    

	    projectRepository.save(pm1);
	    projectRepository.save(pm2);
	    projectRepository.save(pm3);
	    
	    return "forward:list";
	  }

	@RequestMapping("/delete")
	public String deleteDataProject(Model model, @RequestParam int id) {
		projectRepository.delete(id);

		return "forward:list";
	}

	@ExceptionHandler(Exception.class)
	public String handleAllException(Exception ex) {

		return "showError";

	}
}
