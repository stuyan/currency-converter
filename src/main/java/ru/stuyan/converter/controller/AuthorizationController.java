package ru.stuyan.converter.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import ru.stuyan.converter.dto.UserDto;
import ru.stuyan.converter.service.UserService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;

@Controller
public class AuthorizationController {
	
	@Autowired
	private UserService userService;

	@GetMapping("/login")
	public String loginPage(Model model, @RequestParam(name="error",required=false) Boolean error) {
		model.addAttribute("form", new UserDto());
		if (error != null && error) {
			model.addAttribute("error", error);
		}
		return "login";
	}
	
	@GetMapping("/registration")
	public String registrationPage(Model model, @RequestParam(name="error",required=false) Boolean error) {
		model.addAttribute("form", new UserDto());
		if (error != null && error) {
			model.addAttribute("error", error);
		}
		return "registration";
	}
	
	@PostMapping("/registration")
	public String registerUser(HttpServletRequest request, UserDto form) throws ServletException {
		if (!userService.isUserExists(form.getLogin())) {
			userService.saveUser(form);
			request.login(form.getLogin(), form.getPassword());
			return "redirect:";
		}
		return "redirect:login";
	}

 }
