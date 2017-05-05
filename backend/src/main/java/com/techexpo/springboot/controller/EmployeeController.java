package com.techexpo.springboot.controller;

import java.util.LinkedHashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.support.atomic.RedisAtomicInteger;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import com.techexpo.springboot.model.common.Employee;
import com.techexpo.springboot.model.response.EmployeeResponse;
import com.techexpo.springboot.service.EmployeeService;

@RestController
@RequestMapping("/employee")
public class EmployeeController {
	
    private static final Logger LOGGER = LoggerFactory.getLogger(EmployeeController.class);

    @Autowired
    StringRedisTemplate redisTemplate;
    
    @Autowired
    private RedisAtomicInteger atomicInteger; 
    
    private EmployeeService employeeService;
    
    @Autowired
    EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }
    
    @RequestMapping(method = RequestMethod.GET)
    public EmployeeResponse getAllEmployees(){
      LOGGER.info("Getting list of employees");
      atomicInteger.incrementAndGet();
      EmployeeResponse employeeResponse = employeeService.getEmployees();
	  LOGGER.info(employeeResponse.toString());
      return employeeResponse;
    }
    
 	 	
 	@RequestMapping(method = RequestMethod.POST)
 	public Map<String, Object> createEmployee(@RequestBody Map<String, Object> employeeMap){
 		LOGGER.info("Saving Employee Object....");
 		Employee employee = new Employee(employeeMap.get("fname").toString(),employeeMap.get("lname").toString(),Integer.parseInt(employeeMap.get("age").toString()),
 				Double.parseDouble(employeeMap.get("salary").toString()),employeeMap.get("department").toString(),employeeMap.get("title").toString());
	    Map<String, Object> response = new LinkedHashMap<String, Object>();
	    employee = employeeService.saveEmployee(employee);
 		LOGGER.info("Saving Employee [employee] ...." +employee.toString());
	    response.put("message", "Employee created successfully");
	    response.put("employee", employee);
	    return response;
 	}
	
	
	@RequestMapping(method=RequestMethod.GET, value="/count")
	public long count () {
		LOGGER.info("Getting counter....." + atomicInteger.get());
		return atomicInteger.get();
	}
	
}
