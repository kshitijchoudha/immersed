/**
 * 
 */
package com.techexpo.springboot.application;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author Madhu
 *
 */
@SpringBootApplication (scanBasePackages = {"com.techexpo"})
public class Application {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		System.out.println("Starting Spring Boot Application ..................................");
		 SpringApplication.run(Application.class, args);
	}

}
