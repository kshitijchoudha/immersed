/**
 * 
 */
package com.techexpo.springboot.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.techexpo.springboot.model.common.Employee;
import com.techexpo.springboot.model.response.EmployeeResponse;
import com.techexpo.springboot.repository.EmployeeServiceRepository;
import com.techexpo.springboot.service.EmployeeService;

/**
 * @author Madhu
 *
 */

@Service("employeeService")
public class EmployeeServiceImpl implements  EmployeeService {

	@Autowired
	EmployeeServiceRepository repository;

	EmployeeServiceImpl(EmployeeServiceRepository repository)  {
		this.repository = repository;
	}
	
	public Employee saveEmployee(Employee employee) {
		return repository.save(employee);
	}
	
	public EmployeeResponse getEmployees() {
		EmployeeResponse resp = new EmployeeResponse();
		List<Employee> employeeList = repository.findAll();
		resp.setEmployeeList(employeeList);
		return resp;
	}
	
	
}
