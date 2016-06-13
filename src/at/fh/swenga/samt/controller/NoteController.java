package at.fh.swenga.samt.controller;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import at.fh.swenga.samt.dao.NoteRepository;
import at.fh.swenga.samt.dao.UserRepository;
import at.fh.swenga.samt.model.NoteModel;
import at.fh.swenga.samt.model.UserModel;

@Controller
@RequestMapping("/notes")
public class NoteController {

	@Autowired
	NoteRepository noteRepository;
	
	@Autowired
	UserRepository userRepository;

	@RequestMapping(value = { "", "list", "own" })
	public String indexNotes(Model model) {
		
		final UserDetails userdet = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
	    String userName = userdet.getUsername();
	      
	    List<UserModel> user = userRepository.findByUserName(userName);
	    UserModel userModel = user.get(0);
	    
		List<NoteModel> notes = noteRepository.findByUserOrderByIdDesc(userModel);

		model.addAttribute("notes", notes);
		model.addAttribute("type", "findOwnNotes");
		return "notes";
	}

	@RequestMapping("public")
	public String indexNotesPublic(Model model) {
		
		List<NoteModel> notes = noteRepository.findIfPublic();

		model.addAttribute("notes", notes);
		model.addAttribute("type", "findPublicNotes");
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
	public String add(Model model, @RequestParam String name, @RequestParam String content) {
		{
			final UserDetails userdet = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		    String userName = userdet.getUsername();
		      
		    List<UserModel> user = userRepository.findByUserName(userName);
		    UserModel userModel = user.get(0);
		    int user_id = user.get(0).getId();
					
			model.addAttribute(name);
			model.addAttribute(content);	
			model.addAttribute(user_id);

			NoteModel nm = new NoteModel(name, content);
			nm.setUser(userModel);
					
			noteRepository.save(nm);

		}

		return "forward:list";
	}

	@ExceptionHandler(Exception.class)
	public String handleAllException(Exception ex) {

		return "showError";

	}
}