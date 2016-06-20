package at.fh.swenga.samt.controller;

import java.util.Collection;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;

@ControllerAdvice
public class UserControllerAdvice {

	@ModelAttribute
	public void addBugetToModel(Model model) {

		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		String username = auth.getName();

		model.addAttribute("username", username);

		Collection<GrantedAuthority> authorities = (Collection<GrantedAuthority>) SecurityContextHolder.getContext()
				.getAuthentication().getAuthorities();

		for (GrantedAuthority authority : authorities) {
			String userrole = "";
			switch (authority.toString()) {
			case "ROLE_USER":
				userrole = "User";
				break;
			case "ROLE_ADMIN":
				userrole = "Administrator";
				break;
			default:
				userrole = "Guest";
				break;
			}
			model.addAttribute("userrole", userrole);
			if (authority != null) {
				break;
			}
		}

	}
}