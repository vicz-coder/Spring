package com.home.spring.controller;

import javax.validation.ConstraintViolationException;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.DataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.home.spring.dao.IStudentDAO;
import com.home.spring.entity.Student;

@Controller
@RequestMapping("/student")
public class StudentController {

	@Autowired
	IStudentDAO studentDAO;
	
	@InitBinder
	public void initBinder(DataBinder dataBinder) {
		StringTrimmerEditor trimmer = new StringTrimmerEditor(true);
		dataBinder.registerCustomEditor(String.class, trimmer);
	}
	
	@GetMapping("/home")
	public String getHomePage() {
		return "home";
	}
	
	@GetMapping("/signup")
	public String displaySignup(Model model) {
		model.addAttribute("student", new Student());
		return "signup";
	}
	
	@GetMapping("/profile")
	public String displayProfile(Model model,Authentication authentication) {
		model.addAttribute("user", authentication.getPrincipal().toString());
		return "profile";
	}
	
	@GetMapping("/login")
	public String showLogin() {
		return "login";
	}
	
	@PostMapping("/process")
	public String displayConfirmation(@Valid @ModelAttribute("student") Student student,BindingResult result) {
		System.out.println(student);
		System.out.println(result);
		if(result.hasErrors()) {
			return "signup";
		}
		else
		{	try {
			studentDAO.save(student);
		}
		catch (Exception e) {

			if(e.getMessage().contains("ConstraintViolationException"))
			{
				result.reject("unique.student.username");
				return "signup";
			}
			
		}
			
			return "confirmation";
		}
			
	}
	
	
	
}
