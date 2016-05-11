package at.fh.swenga.samt.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HomeController {

	@RequestMapping("/")
	public String index() {
		return "index";
	}

	// @ExceptionHandler(Exception.class)
	public String handleAllException(Exception ex) {

		return "showError";

	}
}