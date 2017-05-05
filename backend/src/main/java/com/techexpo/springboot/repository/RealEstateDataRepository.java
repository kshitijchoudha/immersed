package com.techexpo.springboot.repository;

import org.springframework.data.mongodb.repository.MongoRepository;


import com.techexpo.springboot.model.common.RealEstateData;

public interface RealEstateDataRepository extends MongoRepository<RealEstateData, String> {

	
}
 