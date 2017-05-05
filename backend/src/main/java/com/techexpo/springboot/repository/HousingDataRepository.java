package com.techexpo.springboot.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.techexpo.springboot.model.common.HousingData;

public interface HousingDataRepository extends MongoRepository<HousingData, String> {

	
}
 