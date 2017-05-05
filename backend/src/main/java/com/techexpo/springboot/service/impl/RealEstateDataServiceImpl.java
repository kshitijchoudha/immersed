/**
 * 
 */
package com.techexpo.springboot.service.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.techexpo.springboot.model.common.HousingData;
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

	public ResponseObject getData() {
		ResponseObject responseObject = new ResponseObject();
		List<RealEstateData> realEstateDataList = (List<RealEstateData>) realEstateDataRepository.findAll();
		responseObject.setObjectList(realEstateDataList);
		return responseObject;
	}

	public RealEstateData saveData(RealEstateData realEstateData) {
		return realEstateDataRepository.save(realEstateData);
	}

	public boolean deleteDataById(String id) {
		LOGGER.info("Impl .....Deleting realestate data for id:" + id);
		RealEstateData realEstateData = findDataById(id);
		LOGGER.info("impl RealEstateData:" + realEstateData.toString());
		try {
			realEstateDataRepository.delete(id);
		} catch (Exception e ) {
			return false;
		}
		return true;
	}
	
	public RealEstateData findDataById(String id) {
		LOGGER.info("Finding RealEstateData by id:" + id );
		try {
			RealEstateData realEstateData = realEstateDataRepository.findOne(id);
		LOGGER.info("RealEstateData :" + realEstateData.toString());
		return realEstateData;
		} catch(Exception e) {
			e.printStackTrace();
			return null;
		}
	}

		
	public boolean updateData(RealEstateData realEstateData) {
		try {
			RealEstateData realEstateDataObj = realEstateDataRepository.findOne(realEstateData.getId());
			LOGGER.info("RealEstateData :" + realEstateDataObj.toString());
			realEstateDataRepository.save(realEstateDataObj);			
		} catch(Exception e) {
			e.printStackTrace();
			return false;
		}
		return false;
	}

}
