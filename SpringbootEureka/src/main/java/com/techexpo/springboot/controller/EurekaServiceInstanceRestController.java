package com.techexpo.springboot.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;



@RestController
public class EurekaServiceInstanceRestController {
	
	@Autowired
    private DiscoveryClient discoveryClient;
	
//	@LoadBalanced
	@Autowired
	private RestTemplate restTemplate;
	
	private static final String URL = "http://192.168.1.67:8761/eureka/apps/";
	
	@RequestMapping("/service-instances/{applicationName}")
    public List<ServiceInstance> serviceInstancesByApplicationName(
            @PathVariable String applicationName) {
        return this.discoveryClient.getInstances(applicationName);
    }

	@RequestMapping(value="/service-deactivate/{applicationName}", method = RequestMethod.GET)
    public boolean unregister(@PathVariable String applicationName) {
		System.out.println("Deactivate Service from Eureka starts..." + applicationName);
		String GETSERVICER_URL = URL + applicationName;
		String jsonString = new RestTemplate().getForObject(GETSERVICER_URL, String.class);
		System.out.println("Instance information......" + jsonString);

		HttpHeaders headers = new HttpHeaders();
		HttpEntity entity = new HttpEntity(headers);
		String UNREGISTER_URL = URL + applicationName + "/Kavya.attlocal.net:fm-be:8082/status?value=OUT_OF_SERVICE";
		ResponseEntity<String> deregisterResp
		  = new RestTemplate().exchange(UNREGISTER_URL,HttpMethod.PUT, entity, String.class);
		System.out.println("deregisterResp:");


		System.out.println("Deactivate Service from Eureka ends..." + applicationName);
		return true;
	}
	

	@RequestMapping(value="/service-activate/{applicationName}", method = RequestMethod.GET)
    public boolean activateServicer(@PathVariable String applicationName) {
		System.out.println("Activate Service from Eureka starts..." + applicationName);
		String GETSERVICER_URL = URL + applicationName;
		String jsonString = new RestTemplate().getForObject(GETSERVICER_URL, String.class);
		System.out.println("Instance information......" + jsonString);
		HttpHeaders headers = new HttpHeaders();
		HttpEntity entity = new HttpEntity(headers);
		String UNREGISTER_URL = URL + applicationName + "/Kavya.attlocal.net:fm-be:8082/status?value=UP";
		ResponseEntity<String> deregisterResp
		  = new RestTemplate().exchange(UNREGISTER_URL,HttpMethod.DELETE, entity, String.class);
		System.out.println("deregisterResp:" + deregisterResp);
		System.out.println("Activate Service from Eureka ends..." + applicationName);
		
		return true;
		
	}

	
	@RequestMapping(value="/instances/", method = RequestMethod.GET)
    public List<String> getAll() {
		//RestTemplate restTemplate = new RestTemplate();
		System.out.println("GetAll method.......");
		List<String> instances =  this.discoveryClient.getServices();
		System.out.println("instances method......." + instances);
        return instances;
    }
	
	@RequestMapping(value="/service-instances/", method = RequestMethod.GET)
    public List<String> allserviceInstances() {
		return discoveryClient.getServices();
		
    }
	
	@LoadBalanced
    @Bean
    RestTemplate restTemplate() {
        return new RestTemplate();
    }
	
//	@RequestMapping(value="/service-instances/", method = RequestMethod.GET)
//    public List<String> allserviceInstances() {
//		return discoveryClient.getServices();
//        //return this.discoveryClient.getInstances(applicationName);
//		//return null;
//    }
	
}
