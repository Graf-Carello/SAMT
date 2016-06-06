package at.fh.swenga.samt.controller;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.fluttercode.datafactory.impl.DataFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import at.fh.swenga.samt.dao.NoteRepository;
import at.fh.swenga.samt.model.NoteModel;

@Controller
@RequestMapping("/notes")
public class NoteController {

	@Autowired
	NoteRepository noteRepository;

	@RequestMapping(value = { "", "list", "own" })
	public String indexNotes(Model model) {
		List<NoteModel> notes = noteRepository.findAllByOrderByIdDesc();
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

	/*
	 * @RequestMapping("/fill")
	 * 
	 * @Transactional public String fillDataNotes(Model model) {
	 * 
	 * NoteModel nm1 = new NoteModel("SWENGA Kurs",
	 * "Spring Data Web HÜ machen, bis 11.05."); NoteModel nm2 = new
	 * NoteModel("Math2", "Integralrechnung üben"); NoteModel nm3 = new
	 * NoteModel("SWENGA", "Quiz Security vorbereiten");
	 * 
	 * noteRepository.save(nm1); noteRepository.save(nm2);
	 * noteRepository.save(nm3);
	 * 
	 * return "forward:list"; }
	 */

	@RequestMapping("/delete")
	public String deleteDataNote(Model model, @RequestParam int id) {
		noteRepository.delete(id);

		return "forward:list";
	}

	@RequestMapping(value = "/edit", method = RequestMethod.GET)
	@Transactional
	public String showEditForm(Model model, @RequestParam int id) {

		NoteModel note = noteRepository.findOne(id);
		
		System.out.println(note.getId());

		if (note != null) {
			model.addAttribute("note", note);

			return "editNote";
		} else {
			model.addAttribute("errorMessage", "Couldn't find note " + id);
			return "forward:list";
		}

	}

	@RequestMapping(value = "/edit", method = RequestMethod.POST)
	@Transactional
	public String edit(@Valid @ModelAttribute NoteModel changedNoteModel, BindingResult bindingResult, Model model) {

		if (bindingResult.hasErrors()) {
			String errorMessage = "";
			for (FieldError fieldError : bindingResult.getFieldErrors()) {
				errorMessage += fieldError.getField() + " is invalid<br>";
			}
			model.addAttribute("errorMessage", errorMessage);

			return "forward:list";
		}

		System.out.println(changedNoteModel.getId() + changedNoteModel.getName() + changedNoteModel.getContent());

		
		NoteModel note = noteRepository.findOne(changedNoteModel.getId());

		if (note == null) {
			model.addAttribute("errorMessage", "Note does not exist!<br>");
		} else {

			note.setName(changedNoteModel.getName());
			note.setContent(changedNoteModel.getContent());

			model.addAttribute("message", "Changed note " + changedNoteModel.getId());
		}

		return "forward:list";
	}

	@RequestMapping(value = "/add", method = RequestMethod.GET)
	public String showAddNoteForm(Model model) {

		return "editNote";
	}

	@RequestMapping(value = "/add", method = RequestMethod.POST)
	@Transactional
	public String add(Model model, @RequestParam int id, @RequestParam String name, @RequestParam String content) {
		{
			model.addAttribute(name);
			model.addAttribute(content);

			NoteModel nm = new NoteModel(name, content);
			noteRepository.save(nm);

		}

		return "forward:list";
	}

	@ExceptionHandler(Exception.class)
	public String handleAllException(Exception ex) {

		return "showError";

	}
}