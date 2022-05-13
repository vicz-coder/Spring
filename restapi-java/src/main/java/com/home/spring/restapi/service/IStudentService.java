package com.home.spring.restapi.service;

import java.util.List;

import com.home.spring.restapi.entity.Student;

public interface IStudentService {

	public List<Student> getStudents();
	
	public Student getStudentById(String id);
	
	public Student saveStudent(Student student);
	
	public Student delete(String userId);
	
}
