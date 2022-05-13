package com.home.spring.restapi.entity;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;

@Entity
@Table(name = "student_api")
public class Student {
	
	@Id
	@NotNull
	@NotEmpty
	@Column(name="username")
	private String username;
	
	@NotNull
	@NotEmpty
	@Column(name="first_name")
	private String firstname;
	
	@NotNull
	@NotEmpty
	@Column(name="last_name")
	private String lastname;
	
	@NotNull
	@JsonFormat(pattern = "yyyy-MM-dd")
	@Column(name="dob")
	private Date dob;
	
	@NotNull
	@NotEmpty
	@Column(name="gender")
	private String gender;
	
	@NotNull
	@NotEmpty
	@Column(name="course")
	private String course;
	
	
	public Student() {
		super();
	}
	
	
	public Student(String username, String firstname, String lastname, Date dob, String gender, String course) {
		super();
		this.username = username;
		this.firstname = firstname;
		this.lastname = lastname;
		this.dob = dob;
		this.gender = gender;
		this.course = course;
	}


	public String getUsername() {
		return username;
	}
	
	public void setUsername(String username) {
		this.username = username;
	}
	public String getFirstname() {
		return firstname;
	}
	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}
	public String getLastname() {
		return lastname;
	}
	public void setLastname(String lastname) {
		this.lastname = lastname;
	}
	public Date getDob() {
		return dob;
	}
	public void setDob(Date dob) {
		this.dob = dob;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public String getCourse() {
		return course;
	}
	public void setCourse(String course) {
		this.course = course;
	}


	@Override
	public String toString() {
		return "Student [username=" + username + ", firstname=" + firstname + ", lastname=" + lastname + ", dob=" + dob
				+ ", gender=" + gender + ", course=" + course + "]";
	}
	
	
	
	
}
