package at.fh.swenga.samt.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletResponse;
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
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.gson.Gson;

import at.fh.swenga.samt.dao.EventRepository;
import at.fh.swenga.samt.dao.UserRepository;
import at.fh.swenga.samt.model.EventModel;
import at.fh.swenga.samt.model.UserModel;

@Controller
@RequestMapping("/events")
public class EventController {

	@Autowired
	EventRepository eventRepository;

	@Autowired
	UserRepository userRepository;

	@RequestMapping(value = { "/", "index" })
	public String index(Model model) {
		List<EventModel> events = eventRepository.findAll();
		model.addAttribute("events", events);
		model.addAttribute("type", "findAll");
		model.addAttribute("pageTitle", "Calendar");
		return "events/index";
	}

	@RequestMapping("/delete")
	public String deleteData(Model model, @RequestParam int id) {
		eventRepository.delete(id);

		return "forward:index/";
	}

	@RequestMapping(value = "edit", method = RequestMethod.GET)
	@Transactional
	public String showEditForm(Model model, @RequestParam int id) {

		EventModel event = eventRepository.findOne(id);

		if (event != null) {
			model.addAttribute("event", event);
			return "events/create";
		} else {
			model.addAttribute("errorMessage", "Couldn't find event " + id);
			return "forward:index/";
		}

	}

	@RequestMapping(value = "edit", method = RequestMethod.POST)
	@Transactional
	public String edit(@Valid @ModelAttribute EventModel changedEventModel, BindingResult bindingResult, Model model) {

		if (bindingResult.hasErrors()) {
			String errorMessage = "";
			for (FieldError fieldError : bindingResult.getFieldErrors()) {
				errorMessage += fieldError.getField() + " is invalid<br>";
			}

			model.addAttribute("errorMessage", errorMessage);
			return "forward:edit/";
		}

		EventModel event = eventRepository.findOne(changedEventModel.getId());

		if (event == null) {
			model.addAttribute("errorMessage", "Event does not exist!<br>");
		} else {
			event.setTitle(changedEventModel.getTitle());
			event.setStart(changedEventModel.getStart());
			event.setEnd(changedEventModel.getEnd());
		}

		return "forward:index/";
	}

	@RequestMapping(value = "add", method = RequestMethod.GET)
	public String showAddEventForm(Model model) {

		return "events/create";
	}

	@RequestMapping(value = "add", method = RequestMethod.POST)
	@Transactional
	public String add(Model model, @RequestParam String title, @RequestParam String start,
			@RequestParam String end) {
		{
			final UserDetails userdet = (UserDetails) SecurityContextHolder.getContext().getAuthentication()
					.getPrincipal();
			String stringCreator = userdet.getUsername();

			List<UserModel> userList = userRepository.findByUserName(stringCreator);
			UserModel userModel = userList.get(0);
			
			SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy");
			Date formattedStart = new Date();
			Date formattedEnd = new Date();
			try {
				formattedStart = sdf.parse(start);
				formattedEnd = sdf.parse(end);
			} catch (ParseException e) {
				model.addAttribute("errorMessage", "Date Parsing Error" + e);
			}

			model.addAttribute(title);
			model.addAttribute(formattedStart);
			model.addAttribute(formattedEnd);

			EventModel em = new EventModel(title, formattedStart, formattedEnd);
			em.setUser(userModel);
			eventRepository.save(em);
		}

		return "forward:/index";
	}

	@RequestMapping(value = { "/eventEntries", "json" }, produces = "application/json")
	public @ResponseBody String getCalendarJson(HttpServletResponse response) {

		final UserDetails userdet = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		String stringCreator = userdet.getUsername();
		
		List<UserModel> userList = userRepository.findByUserName(stringCreator);
		int intCreator = userList.get(0).getId();

		response.setCharacterEncoding("UTF-8");

		String json = new Gson().toJson(eventRepository.findByUserId(intCreator));
		return json;
	}

	@ExceptionHandler(Exception.class)
	public String handleAllException(Exception ex) {

		return "showError";

	}

} 
