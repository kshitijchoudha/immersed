package com.techexpo.springboot.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.techexpo.springboot.model.common.Employee;
import com.techexpo.springboot.model.response.EmployeeResponse;

@RestController
public class EmployeeController {

	@RequestMapping(method=RequestMethod.GET, value="/list")
	public EmployeeResponse listOfEmployee() {
		EmployeeResponse response =populateResponse();
		return response;
	}
	
	private EmployeeResponse populateResponse() {
		EmployeeResponse response = new EmployeeResponse();
		List<Employee> empList = new ArrayList<Employee>();
		
		Employee emp = new Employee();
		emp.setFname("John");
		emp.setLname("Dohrthy");
		emp.setAge(45);
		emp.setDeportment("Design");
		emp.setSalary(Double.parseDouble("100000.00"));
		emp.setTitle("Sr Engineer");
		empList.add(emp);
		
		emp = new Employee();
		emp.setFname("James");
		emp.setLname("Adam");
		emp.setAge(50);
		emp.setDeportment("Engineering");
		emp.setSalary(Double.parseDouble("120000.00"));
		emp.setTitle("Manager");
		empList.add(emp);
		
		
		response.setEmployeeList(empList);
		return response;
	}
	
}
