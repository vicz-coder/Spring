package com.home.spring.entity;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import com.home.spring.validation.GradeCheck;
import com.home.spring.validation.PasswordCheck;

@Entity
@PasswordCheck
@Table(name = "student_java")
public class Student {
	
	@NotNull
	@Column(name="first_name")
	private String firstName;
	
	@NotNull
	@Column(name="last_name")
	private String lastName;
	
	@NotNull
	@Id
	@Column(name="username")
	@Pattern(regexp = "^[a-z0-9]{4,12}+$",message = "Only alphanumeric with 4 to 6 character")
	private String userName;
	
	@NotNull
	@Column(name="dob")
	private Date dob;
	
	@NotNull
	@Size(min = 4,max = 10)
	@Column(name="password")
	private String password;
	
	@NotNull
	@Transient
	private String confirmPassword;
	
	@GradeCheck
	@Column(name="grade")
	private String grade;

	private String gender;
	
	enum SEX{
		MALE,
		FEMALE
	}
	
	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public Date getDob() {
		return dob;
	}

	public void setDob(Date dob) {
		this.dob = dob;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getConfirmPassword() {
		return confirmPassword;
	}

	public void setConfirmPassword(String confirmPassword) {
		this.confirmPassword = confirmPassword;
	}
	
	

	public String getGrade() {
		return grade;
	}

	public void setGrade(String grade) {
		this.grade = grade;
	}

	@Override
	public String toString() {
		return "Student [firstname=" + firstName + ", lastName=" + lastName + ", userName=" + userName + ", dob=" + dob
				+ ", password=" + password + ", confirmPassword=" + confirmPassword + "]";
	}

	public Student() {
		super();
	}
	
	

}
