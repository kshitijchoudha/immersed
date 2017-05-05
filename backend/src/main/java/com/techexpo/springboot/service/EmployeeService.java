/**
 * 
 */
package com.techexpo.springboot.service;

import com.techexpo.springboot.model.common.Employee;
import com.techexpo.springboot.model.response.EmployeeResponse;

/**
 * @author Madhu
 *
 */
public interface EmployeeService {

	public EmployeeResponse getEmployees();
	
	public Employee saveEmployee(Employee employee);
		
}
