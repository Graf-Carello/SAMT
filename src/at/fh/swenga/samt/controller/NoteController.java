package at.fh.swenga.samt.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import at.fh.swenga.samt.dao.NoteRepository;
import at.fh.swenga.samt.model.NoteModel;

@Controller
@RequestMapping("/notes")
public class NoteController {

	@Autowired
	NoteRepository noteRepository;

	@RequestMapping("")
	public String indexNotes(Model model) {
		List<NoteModel> notes = noteRepository.findAll();
		model.addAttribute("notes", notes);
		model.addAttribute("type", "findAllNotes");
		return "notes";
	}

	@RequestMapping(value = { "/getPage" })
	public String getPageNotes(Pageable page, Model model) {

		Page<NoteModel> notes = noteRepository.findAll(page);
		model.addAttribute("notes", notes.getContent());
		model.addAttribute("usersPage", notes);

		return "notes";
	}

	@RequestMapping(value = { "/find" })
	public String findNote(Model model, @RequestParam String searchString, @ModelAttribute("type") String type) {
		List<NoteModel> notes = null;
		int count = 0;

		switch (type) {
		case "findAll":
			notes = noteRepository.findAll();
			break;

		default:
			notes = noteRepository.findAll();
		}

		model.addAttribute("notes", notes);
		model.addAttribute("count", count);
		return "notes";
	}

	@RequestMapping(value = { "/findById" })
	public String findByIdNote(@RequestParam("id") NoteModel g, Model model) {
		List<NoteModel> notes = new ArrayList<>();
		notes.add(g);
		model.addAttribute("notes", notes);

		return "notes";
	}

	@RequestMapping("/fill")
	@Transactional
	public String fillDataNotes(Model model) {

		NoteModel nm1 = new NoteModel("SWENGA Kurs", "Spring Data Web HÜ machen, bis 11.05.");
		NoteModel nm2 = new NoteModel("Math2", "Integralrechnung üben");
		NoteModel nm3 = new NoteModel("SWENGA", "Quiz Security vorbereiten");

		noteRepository.save(nm1);
		noteRepository.save(nm2);
		noteRepository.save(nm3);

		return "forward:list";
	}

	@RequestMapping("/delete")
	public String deleteDataNote(Model model, @RequestParam int id) {
		noteRepository.delete(id);

		return "forward:list";
	}

	// @ExceptionHandler(Exception.class)
	public String handleAllException(Exception ex) {

		return "showError";

	}
}
