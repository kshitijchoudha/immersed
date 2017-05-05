package com.techexpo.springboot.repository;

import java.util.Map;

import javax.annotation.PostConstruct;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.StringRedisTemplate;

import com.techexpo.springboot.model.common.RealEstateData;

public class RedisRepository {

	private static final Logger LOGGER = LoggerFactory.getLogger(RedisRepository.class);

	
	private static final String KEY = "RealEstate";
	private StringRedisTemplate redisTemplate;
	private HashOperations hashOps;
	
	public RedisRepository() {}
	
	@Autowired
	public RedisRepository(StringRedisTemplate redisTemplate) {
        this.redisTemplate = redisTemplate;
    }
	
	@PostConstruct
    private void init() {
        hashOps = redisTemplate.opsForHash();
    }
	
	public void saveRealEstate(RealEstateData data) {
		LOGGER.info("pushing data....");
		hashOps.put(KEY, data.getRealEstateId(), data.toString());
	}
	
	public Map<Object, Object> findAllRealEstateData() {
		LOGGER.info("reading data from Redis cache....");
		return hashOps.entries(KEY);
	}
	
	public String findDataById(String id) {
		return (String) hashOps.get(KEY, id);
	}
}
