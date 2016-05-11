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

import at.fh.swenga.samt.dao.GradeRepository;
import at.fh.swenga.samt.model.GradeModel;

@Controller
@RequestMapping("/grades")
public class GradeController {

	@Autowired
	GradeRepository gradeRepository;

	@RequestMapping("")
	public String indexGrades(Model model) {
		List<GradeModel> grades = gradeRepository.findAll();
		model.addAttribute("grades", grades);
		model.addAttribute("type", "findAllGrades");
		return "grades";
	}

	@RequestMapping(value = { "/getPage" })
	public String getPageGrades(Pageable page, Model model) {

		Page<GradeModel> grades = gradeRepository.findAll(page);
		model.addAttribute("grades", grades.getContent());
		model.addAttribute("usersPage", grades);

		return "grades";
	}

	@RequestMapping(value = { "/find" })
	public String findGrade(Model model, @RequestParam String searchString, @ModelAttribute("type") String type) {
		List<GradeModel> grades = null;
		int count = 0;

		switch (type) {
		case "findAll":
			grades = gradeRepository.findAll();
			break;

		default:
			grades = gradeRepository.findAll();
		}

		model.addAttribute("grades", grades);
		model.addAttribute("count", count);
		return "grades";
	}

	@RequestMapping(value = { "/findById" })
	public String findByIdGrade(@RequestParam("id") GradeModel g, Model model) {
		List<GradeModel> grades = new ArrayList<>();
		grades.add(g);
		model.addAttribute("grades", grades);

		return "grades";
	}

	@RequestMapping("/fill")
	@Transactional
	public String fillDataGrades(Model model) {

		Date now = new Date();
		GradeModel gm1 = new GradeModel("SWENGA", now);
		GradeModel gm2 = new GradeModel("Math2", now);
		GradeModel gm3 = new GradeModel("SWENGA", now);

		gradeRepository.save(gm1);
		gradeRepository.save(gm2);
		gradeRepository.save(gm3);

		return "forward:list";
	}

	@RequestMapping("/delete")
	public String deleteDataGrade(Model model, @RequestParam int id) {
		gradeRepository.delete(id);

		return "forward:list";
	}

	@ExceptionHandler(Exception.class)
	public String handleAllException(Exception ex) {

		return "showError";

	}
}
