/**
 * 
 */
package com.techexpo.springboot.service.impl;

import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.techexpo.springboot.model.common.RealEstateData;
import com.techexpo.springboot.model.response.ResponseObject;
import com.techexpo.springboot.repository.RealEstateDataRepository;
import com.techexpo.springboot.repository.RedisRepository;
import com.techexpo.springboot.service.RealEstateDataService;

/**
 * @author Madhu
 *
 */
@Service("realEstateDataService")
public class RealEstateDataServiceImpl implements RealEstateDataService {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(RealEstateDataServiceImpl.class);
		
	@Autowired
	RealEstateDataRepository realEstateDataRepository;
	
	@Autowired
	RedisRepository redisRepository;

	public ResponseObject getAllRealEstateData() {
		ResponseObject responseObject = new ResponseObject();
		List<RealEstateData> realEstateDataList = (List<RealEstateData>) realEstateDataRepository.findAll();
		responseObject.setObjectList(realEstateDataList);
		pushDataToRedis(realEstateDataList);
		findAllRealEstateData();
		return responseObject;
	}

	public void pushDataToRedis(List<RealEstateData> realEstateDataList) {
		for (RealEstateData data: realEstateDataList) {
			if(!findDatainRedisCache(data))
				pushRealEstataDataTORedis(data);
		}
	}
	
	private boolean findDatainRedisCache(RealEstateData data) {
		String existingData = redisRepository.findDataById(data.getRealEstateId());
		if(null != existingData ) {
			LOGGER.info("-----------------------Exisitng Data:: " + existingData);
			return true;
		} 
		return false;
	}
	
	private void findAllRealEstateData() {
		Map<Object, Object>  datas = redisRepository.findAllRealEstateData();
		for(Entry<Object, Object> entry: datas.entrySet()) {
			LOGGER.info("Key:" + entry.getKey() + " ::: Value:" + entry.getValue());
		}
	}
	
	private void pushRealEstataDataTORedis(RealEstateData data) {
		LOGGER.info("Saving data to Redis cache");
		redisRepository.saveRealEstate(data);
		LOGGER.info("Saved data to Redis cache.......");
	}
	
	public RealEstateData saveRealEstate(RealEstateData realEstateData) {
		return realEstateDataRepository.save(realEstateData);
		
	}

	public boolean deleteByRealEstateId(String realEstateId) {
		LOGGER.info("Impl .....Deleting realestate data for realEstateId:" + realEstateId);
		RealEstateData realEstateData = findByRealEstateId(realEstateId);
		LOGGER.info("impl RealEstateData:" + realEstateData.toString());
		try {
			realEstateDataRepository.delete(realEstateId);
		} catch (Exception e ) {
			return false;
		}
		return true;
	}
	
	public RealEstateData findByRealEstateId(String realEstateId) {
		LOGGER.info("Finding RealEstateData by realEstateId:" + realEstateId );
		try {
			RealEstateData realEstateData = realEstateDataRepository.findOne(realEstateId);
		LOGGER.info("RealEstateData :" + realEstateData.toString());
		return realEstateData;
		} catch(Exception e) {
			e.printStackTrace();
			return null;
		}
	}

		
	public boolean updateRealEstate(RealEstateData realEstateData) {
		try {
			RealEstateData realEstateDataObj = realEstateDataRepository.findOne(realEstateData.getRealEstateId());
			realEstateDataObj = realEstateDataRepository.save(realEstateData);			
			LOGGER.info("Update RealEstateData :" + realEstateDataObj.toString());
		} catch(Exception e) {
			e.printStackTrace();
			return false;
		}
		return false;
	}

}
