package com.home.spring.restapi.controller;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.validation.DataBinder;
import org.springframework.web.bind.MissingPathVariableException;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.home.spring.restapi.entity.Student;
import com.home.spring.restapi.service.IStudentService;

@RestController
@RequestMapping("/api")
public class StudentRestController {
	
	
	
	@Autowired
	IStudentService studentService;
	
	@InitBinder
	public void initBinder(DataBinder dataBinder) {
		StringTrimmerEditor editor = new StringTrimmerEditor(true);
		dataBinder.registerCustomEditor(String.class, editor);
	}
	
	@GetMapping("/students")
	public List<Student> getStudents() {
		List<Student> lStudent = new ArrayList<Student>();
		lStudent = studentService.getStudents();
		return lStudent;
	}
	
	
	@GetMapping("/students/{userId}")
	public Student getStudentById(@PathVariable String userId ) {
		Student s = studentService.getStudentById(userId);
		return s;
	}
	
	
	@PostMapping("/students")
	public Student createStudent(@Valid @RequestBody Student student) {
		return studentService.saveStudent(student);
	}
	
	@PutMapping("/students")
	public Student updateStudent(@RequestBody Student student) {
		System.out.println(student.toString());
		return studentService.saveStudent(student);
	}
	
	@DeleteMapping("/students/{userId}")
	public void delete(@PathVariable(required = false) String userId) throws Exception{
		System.out.println("Deleteing");
		if(userId == null) throw new MissingPathVariableException("userId", null);
		studentService.delete(userId);
	}
}
