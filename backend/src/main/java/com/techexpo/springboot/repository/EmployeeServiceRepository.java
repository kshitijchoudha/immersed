package com.techexpo.springboot.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.techexpo.springboot.model.common.Employee;

public interface EmployeeServiceRepository extends MongoRepository<Employee, String> {

	
}
 