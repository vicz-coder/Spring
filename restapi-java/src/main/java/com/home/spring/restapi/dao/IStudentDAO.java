package com.home.spring.restapi.dao;

import java.util.List;

import com.home.spring.restapi.entity.Student;

public interface IStudentDAO {
	
	public List<Student> getStudents();
	
	public Student getStudentById(String id);
	
	public Student createStudent(Student student);
	
	public Student delete(String userId);
}
