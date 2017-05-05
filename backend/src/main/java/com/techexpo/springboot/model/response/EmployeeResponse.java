package com.techexpo.springboot.model.response;

import java.util.List;

import com.techexpo.springboot.model.common.Employee;

public class EmployeeResponse {

	List <Employee> employeeList ;

	public List<Employee> getEmployeeList() {
		return employeeList;
	}

	public void setEmployeeList(List<Employee> employeeList) {
		this.employeeList = employeeList;
	}

	@Override
	public String toString() {
		return "EmployeeResponse [employeeList=" + employeeList + "]";
	}
	
	
	
}
