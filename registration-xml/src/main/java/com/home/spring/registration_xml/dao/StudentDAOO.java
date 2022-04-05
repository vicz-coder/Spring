package com.home.spring.registration_xml.dao;

import java.sql.Date;

import com.home.spring.registration_xml.entity.Student;

public interface StudentDAOO {
	public void save(String firstName,String lastName,Date dob,String password,String gender,String email);

	public Student getStudent(String userid);
}
