package com.home.spring.dao;

import com.home.spring.entity.Student;

public interface IStudentDAO {
	
	public void save(Student student);

	public Student getStudent(String userName);
}
