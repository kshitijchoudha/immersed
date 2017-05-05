/**
 * 
 */
package com.techexpo.springboot.service.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.techexpo.springboot.model.common.RealEstateData;
import com.techexpo.springboot.model.response.ResponseObject;
import com.techexpo.springboot.repository.RealEstateDataRepository;
import com.techexpo.springboot.service.RealEstateDataService;

/**
 * @author Madhu
 *
 */
@Service("realEstateDataService")
public class RealEstateDataServiceImpl implements RealEstateDataService {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(HousingDataServiceImpl.class);
		
	@Autowired
	RealEstateDataRepository realEstateDataRepository;

	public ResponseObject getAllRealEstateData() {
		ResponseObject responseObject = new ResponseObject();
		List<RealEstateData> realEstateDataList = (List<RealEstateData>) realEstateDataRepository.findAll();
		responseObject.setObjectList(realEstateDataList);
		return responseObject;
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
