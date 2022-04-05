package com.home.spring.registration_xml.dao;

import java.sql.Date;
import java.util.logging.Logger;

import javax.persistence.Query;
import javax.transaction.Transactional;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Repository;

import com.home.spring.registration_xml.entity.Student;

@Repository
public class StudentDAOImpl implements StudentDAOO {

	@Autowired
	SessionFactory sessionFactory;
	
	@Autowired
	PasswordEncoder bcryptEncoder;
	
	Logger logger = Logger.getLogger(getClass().getName());
	
	@Transactional
	@Override
	public void save(String firstName,String lastName,Date dob,String password,String gender,String email) {
		Student newStudent = new Student();

		newStudent.setFirstname(firstName);
		newStudent.setDob(dob);
		newStudent.setLastname(lastName);
		newStudent.setEmail(email);
		newStudent.setGender(gender);
		newStudent.setPassword(bcryptEncoder.encode(password));


		// TODO Auto-generated method stub
		logger.info("Insert Process Started");
		Session session = sessionFactory.getCurrentSession();
		session.save(newStudent);
		logger.info("Insert Process Completed");

	}

	@Transactional
	@Override
	public Student getStudent(String userid) {
		Session session = sessionFactory.getCurrentSession();
		Query query = session.createQuery("from Student where email = :email",Student.class);
		query.setParameter("email", userid);
		
		Student s= (Student)query.getSingleResult();
		return s;
	}

}
