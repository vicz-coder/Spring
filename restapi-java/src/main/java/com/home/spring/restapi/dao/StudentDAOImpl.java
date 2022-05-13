package com.home.spring.restapi.dao;

import java.util.List;


import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.home.spring.restapi.entity.Student;
import com.home.spring.restapi.exception.RecordNotFound;

@Repository
public class StudentDAOImpl implements IStudentDAO{

	@Autowired
	SessionFactory sessionFactory;
	
	@Override
	public List<Student> getStudents() {
		Session session = sessionFactory.getCurrentSession();
		
		Query<Student> query = session.createQuery("from Student",Student.class);
		List<Student> lStudent =  query.getResultList();
		
		return lStudent;
	}

	@Override
	public Student getStudentById(String id) {
		Session session = sessionFactory.getCurrentSession();
		Student s = session.get(Student.class, id);
		if(s==null) 
				throw new RecordNotFound("No record found for "+id);
		return s;
	}


	

	@Override
	public Student delete(String userId) {
		Session session = sessionFactory.getCurrentSession();
		Student s = session.get(Student.class, userId);
		if(s!=null)
			session.delete(s);
		else 
			throw new RecordNotFound("No record found for "+userId);
		return null;
	}

	@Override
	public Student createStudent(Student student) {
		Session session = sessionFactory.getCurrentSession();
		session.saveOrUpdate(student);
		return student;
	}

	
}
