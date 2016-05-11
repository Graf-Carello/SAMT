package at.fh.swenga.samt.controller;

import java.util.ArrayList;
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

import at.fh.swenga.samt.dao.ForumRepository;
import at.fh.swenga.samt.model.ForumModel;

@Controller
@RequestMapping("/forum")
public class ForumController {

	@Autowired
	ForumRepository forumRepository;

	@RequestMapping("")
	public String index(Model model) {
		List<ForumModel> forum = forumRepository.findAll();
		model.addAttribute("forum", forum);
		model.addAttribute("type", "findAll");
		return "index";
	}

	@RequestMapping(value = { "/getPage" })
	public String getPage(Pageable page, Model model) {

		Page<ForumModel> forum = forumRepository.findAll(page);
		model.addAttribute("forum", forum.getContent());
		model.addAttribute("usersPage", forum);

		return "index";
	}

	@RequestMapping(value = { "/find" })
	public String find(Model model, @RequestParam String searchString, @ModelAttribute("type") String type) {
		List<ForumModel> forum = null;
		int count = 0;

		switch (type) {
		case "findAll":
			forum = forumRepository.findAll();
			break;

		default:
			forum = forumRepository.findAll();
		}

		model.addAttribute("forum", forum);
		model.addAttribute("count", count);
		return "index";
	}

	@RequestMapping(value = { "/findById" })
	public String findById(@RequestParam("id") ForumModel f, Model model) {
		List<ForumModel> forum = new ArrayList<>();
		forum.add(f);
		model.addAttribute("forum", forum);

		return "index";
	}

	@RequestMapping("/fill")
	@Transactional
	public String fillData(Model model) {

		ForumModel fm1 = new ForumModel("SWENGA Homework HELP!!!", "I need help! I don't have a clue", "1", true);
		ForumModel fm2 = new ForumModel("Christmas party", "Who wants a christmas party?", "2", true);
		ForumModel fm3 = new ForumModel("SWENGA Homework HELP!!", "No. Just read the error message!", "1", false);

		forumRepository.save(fm1);
		forumRepository.save(fm2);
		forumRepository.save(fm3);
		
		return "forward:list";
	}

	@RequestMapping("/delete")
	public String deleteData(Model model, @RequestParam int id) {
		forumRepository.delete(id);

		return "forward:list";
	}

	@ExceptionHandler(Exception.class)
	public String handleAllException(Exception ex) {

		return "showError";

	}
}
