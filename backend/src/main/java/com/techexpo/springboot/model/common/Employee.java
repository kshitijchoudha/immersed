package com.techexpo.springboot.model.common;

import java.io.Serializable;

import org.springframework.data.annotation.Id;

public class Employee implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	private String id;
	
	private String fname;
	private String lname;
	private int age;
	private Double salary;
	private String deportment;
	private String title;

	public Employee() {
	}

	public Employee(String fname, String lname, int age, Double salary, String deportment, String title) {
		super();
		this.fname = fname;
		this.lname = lname;
		this.age = age;
		this.salary = salary;
		this.deportment = deportment;
		this.title = title;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getFname() {
		return fname;
	}

	public void setFname(String fname) {
		this.fname = fname;
	}

	public String getLname() {
		return lname;
	}

	public void setLname(String lname) {
		this.lname = lname;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public Double getSalary() {
		return salary;
	}

	public void setSalary(Double salary) {
		this.salary = salary;
	}

	public String getDeportment() {
		return deportment;
	}

	public void setDeportment(String deportment) {
		this.deportment = deportment;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	@Override
	public String toString() {
		return "Employee [id=" + id + ", fname=" + fname + ", lname=" + lname + ", age=" + age + ", salary=" + salary
				+ ", deportment=" + deportment + ", title=" + title + "]";
	}

	
}
