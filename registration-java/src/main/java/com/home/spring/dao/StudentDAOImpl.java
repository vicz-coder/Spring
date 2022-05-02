package com.home.spring.dao;

import java.util.logging.Logger;

import javax.persistence.Query;
import javax.transaction.Transactional;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Repository;

import com.home.spring.entity.Student;

@Repository
public class StudentDAOImpl implements IStudentDAO {
	
	@Autowired
	BCryptPasswordEncoder encoder;

	@Autowired
	SessionFactory sessionFactory;
	
	Logger logger = Logger.getLogger(getClass().getName());
	
	@Override
	@Transactional
	public void save(Student student) {
		// TODO Auto-generated method stub
		logger.info("Saving Student Object");
		
		Session session =  sessionFactory.getCurrentSession();
		student.setPassword(encoder.encode(student.getPassword()));
		//session.beginTransaction();
		session.save(student);
		//session.getTransaction().commit();
		

	}

	@Override
	@Transactional
	public Student getStudent(String userName) {

		Session session = sessionFactory.getCurrentSession();
		
		Student student = (Student)session.get(Student.class, userName);
		if(student!=null)
			logger.info(student.toString());
		return student;
	}

}
