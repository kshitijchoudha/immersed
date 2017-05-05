package com.techexpo.springboot.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
//import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.support.atomic.RedisAtomicInteger;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.techexpo.springboot.repository.RedisRepository;


@Configuration
//@EnableJpaRepositories(basePackages = {"com.techexpo.springboot.repository"})
@EnableTransactionManagement
@ComponentScan
public class RedisApplicationConfig {

	@Bean
	public JedisConnectionFactory connectionFactory() {
		JedisConnectionFactory connectionFactory = new JedisConnectionFactory();
		//connectionFactory.setHostName("redis");
		//connectionFactory.setPort(6379);
		return connectionFactory;
	}  
	
	@Bean
	public RedisAtomicInteger init(RedisConnectionFactory factory) { 
	    return new RedisAtomicInteger("myCounter", factory); 
	} 
	
	@Bean
	public StringRedisTemplate redisTemplate() {
		StringRedisTemplate redisTemplate = new StringRedisTemplate();
		redisTemplate.setConnectionFactory(connectionFactory());
		return redisTemplate;
	}
	
	@Bean
	public RedisRepository RedisRepository() {
		RedisRepository redisRepository = new RedisRepository(redisTemplate());
		return redisRepository;
	}
	
}
