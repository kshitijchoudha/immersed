package com.techexpo.springboot.application;

import java.util.concurrent.atomic.AtomicLong;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {
	
	private final AtomicLong counter = new AtomicLong();
	
	@RequestMapping(method=RequestMethod.POST, value="/hello")
	public String hello (@RequestParam(value="name", defaultValue="CMFT Team") String name){
		counter.getAndIncrement();
		return name + " Welcome to Tech Expo !";
	}
	
	@RequestMapping(method=RequestMethod.GET, value="/count1")
	public long count () {
		return counter.get();
	}
	
	

}
