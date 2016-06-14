package at.fh.swenga.samt.controller;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
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

	@RequestMapping(value={"/", "own/"})
	public String indexNotes(Model model) {

		final UserDetails userdet = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		String userName = userdet.getUsername();

		List<UserModel> user = userRepository.findByUserName(userName);
		UserModel userModel = user.get(0);

		List<NoteModel> notes = noteRepository.findByUserOrderByIdDesc(userModel);

		model.addAttribute("notes", notes);
		model.addAttribute("type", "findOwnNotes");
		model.addAttribute("title", "All your notes");
		return "notes/index";
	}

	@RequestMapping("public/")
	public String indexNotesPublic(Model model) {

		List<NoteModel> notes = noteRepository.findIfPublic();
		List<String> authors = new ArrayList<String>();

		for (Iterator<NoteModel> note = notes.iterator(); note.hasNext();) {
			NoteModel cnote = note.next();
			String author = noteRepository.findAuthor(cnote.getId());
			authors.add(author);
		}

		model.addAttribute("notes", notes);
		model.addAttribute("authors", authors);
		System.out.println(authors);
		model.addAttribute("type", "public");
		model.addAttribute("title", "All public notes");
		return "notes/index";
	}

	@RequestMapping("/delete")
	public String deleteDataNote(Model model, @RequestParam int id) {
		noteRepository.delete(id);
		return "forward:own/";
	}

	@RequestMapping(value = "/edit", method = RequestMethod.GET)
	@Transactional
	public String showEditForm(Model model, @RequestParam int id) {

		NoteModel note = noteRepository.findOne(id);

		if (note != null) {
			model.addAttribute("note", note);

			return "notes/create";
		} else {
			model.addAttribute("errorMessage", "Couldn't find note " + id);
			return "forward:own/";
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
			return "forward:own/";
		}

		NoteModel note = noteRepository.findOne(changedNoteModel.getId());

		if (note == null) {
			model.addAttribute("errorMessage", "Note does not exist!<br>");
		} else {

			note.setName(changedNoteModel.getName());
			note.setContent(changedNoteModel.getContent());

			if (changedNoteModel.getIsPublic() == null) {
				note.setIsPublic(false);
			} else {
				note.setIsPublic(changedNoteModel.getIsPublic());
			}
		}

		return "forward:own/";
	}

	@RequestMapping(value = "add", method = RequestMethod.GET)
	public String showAddNoteForm(Model model) {

		return "notes/create";
	}

	@RequestMapping(value = "add", method = RequestMethod.POST)
	@Transactional
	public String add(Model model, @RequestParam String name, @RequestParam String content,
			@RequestParam(value="isPublic", required = false) Boolean isPublic) {
		{
			final UserDetails userdet = (UserDetails) SecurityContextHolder.getContext().getAuthentication()
					.getPrincipal();
			String userName = userdet.getUsername();

			List<UserModel> user = userRepository.findByUserName(userName);
			UserModel userModel = user.get(0);
			int user_id = user.get(0).getId();

			model.addAttribute(name);
			model.addAttribute(content);
			model.addAttribute(user_id);
			model.addAttribute(isPublic);
			

			NoteModel nm = new NoteModel(name, content);
			nm.setUser(userModel);

			if (isPublic == null) {
				nm.setIsPublic(false);
			} else {
				nm.setIsPublic(isPublic);
			}
			
			noteRepository.save(nm);

		}

		return "forward:own/";
	}

	@ExceptionHandler(Exception.class)
	public String handleAllException(Exception ex) {

		return "showError";

	}
}