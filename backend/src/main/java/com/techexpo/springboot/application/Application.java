/**
 * 
 */
package com.techexpo.springboot.application;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Import;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

import com.techexpo.springboot.config.RedisApplicationConfig;

/**
 * @author Madhu
 *
 */
@SpringBootApplication
@ComponentScan ({"com.techexpo"})
@EnableMongoRepositories ("com.techexpo.springboot.repository")
@Import({RedisApplicationConfig.class})
@EnableDiscoveryClient
@EnableHystrix
public class Application {


    private static final Logger LOGGER = LoggerFactory.getLogger(Application.class);
    
	/**
	 * @param args
	 * @throws InterruptedException 
	 */
	public static void main(String[] args) throws InterruptedException {
		LOGGER.info("Starting Tech Imersion Spring Boot Application ..................................");
		SpringApplication.run(Application.class, args);
	}

}
