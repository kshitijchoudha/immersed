package com.techexpo.springboot.model.common;

public class Employee {

	private String fname;
	private String lname;
	private int age;
	private Double salary;
	private String deportment;
	private String title;
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
		return "Employee [fname=" + fname + ", lname=" + lname + ", age=" + age + ", salary=" + salary + ", deportment="
				+ deportment + ", title=" + title + "]";
	}
	
	
	
}
