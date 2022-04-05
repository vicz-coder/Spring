package com.home.spring.registration_xml.controller;






import java.security.Principal;
import java.util.logging.Logger;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.DataBinder;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.home.spring.registration_xml.dao.StudentDAOO;
import com.home.spring.registration_xml.entity.Student;

@Controller
@RequestMapping("/student")
public class HomeController {
	@Autowired
	StudentDAOO studentDAO;

	Logger logger = Logger.getLogger(getClass().getName());
			
	@InitBinder
	public void preformat(WebDataBinder binder) {
		StringTrimmerEditor editor = new StringTrimmerEditor(true);
		binder.registerCustomEditor(String.class, editor);
	}
	
	@GetMapping("/home")
	public String getHomePage(Model model) {
		model.addAttribute("title", "Home Page");
		return "home";
	}
	
	@GetMapping("/registration")
	public String getSignupPage(Model model) {
		model.addAttribute("student", new Student());
		model.addAttribute("title", "Signup");
		return "signup";
	}
	
	@GetMapping("/profile")
	public String getProfile(Model model,Authentication authentication) {
		System.out.println(authentication.getName());
		model.addAttribute("name", authentication.getName());
		return "profile";
	}
	
	@PostMapping("/confirm")
	public String getSignupPage(@Valid  @ModelAttribute Student student,BindingResult result) {
		logger.info(student.toString());
		System.out.println(result);
		logger.info(result.hasErrors() + "");
		if(result.hasErrors()) {
			return "signup";
		}
		else {
			studentDAO.save(student.getFirstname(),student.getLastname(),student.getDob(),student.getPassword(),student.getGender(),student.getEmail());
			return "redirect:/student/home";
		}
		
		
	}
}
