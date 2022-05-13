package com.home.spring.restapi.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.home.spring.restapi.dao.IStudentDAO;
import com.home.spring.restapi.entity.Student;


@Service
public class StudentServiceImpl implements IStudentService{

	@Autowired
	IStudentDAO studentDAO;
	
	@Override
	@Transactional
	public List<Student> getStudents() {
		// TODO Auto-generated method stub
		return studentDAO.getStudents();
	}

	@Override
	@Transactional
	public Student getStudentById(String id) {
		// TODO Auto-generated method stub
		return studentDAO.getStudentById(id);
	}

	@Override
	@Transactional
	public Student saveStudent(Student student) {
		// TODO Auto-generated method stub
		return studentDAO.createStudent(student);
	}

	@Override
	@Transactional
	public Student delete(String userId) {
		return studentDAO.delete(userId);
	}

}
