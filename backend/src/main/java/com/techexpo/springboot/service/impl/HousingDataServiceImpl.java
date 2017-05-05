/**
 * 
 */
package com.techexpo.springboot.service.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.techexpo.springboot.controller.HousingController;
import com.techexpo.springboot.model.common.HousingData;
import com.techexpo.springboot.model.response.ResponseObject;
import com.techexpo.springboot.repository.HousingDataRepository;
import com.techexpo.springboot.service.HousingDataService;

/**
 * @author Madhu
 *
 */

@Service("housingDataService")
public class HousingDataServiceImpl implements  HousingDataService {

    private static final Logger LOGGER = LoggerFactory.getLogger(HousingDataServiceImpl.class);

	
	@Autowired
	HousingDataRepository repository;

	
	public ResponseObject getHousingData() {
		ResponseObject responseObject = new ResponseObject();
		List<HousingData> housingDataList = (List<HousingData>) repository.findAll();
		responseObject.setObjectList(housingDataList);
		return responseObject;
	}
	
	
	public HousingData saveHousingData(HousingData housingData) {
		return repository.save(housingData);
	}


	public boolean deleteHousingData(String id) {
		LOGGER.info("Impl .....Deleting housing data for id:" + id);
		HousingData data = findHousingData(id);
		LOGGER.info("impl Housing Data:" + data.toString());
		try {
			repository.delete(id);
		} catch (Exception e ) {
			return false;
		}
		return true;
	}


	public HousingData findHousingData(String id) {
		LOGGER.info("impl Finding housing data for id:" + id );
		try {
		HousingData data = repository.findOne(id);
		LOGGER.info("Housing Data:" + data.toString());
		return data;
		} catch(Exception e) {
			e.printStackTrace();
			return null;
		}
	}


	public boolean updateHousingData(HousingData housingData) {
		try {
			HousingData data = repository.findOne(housingData.getId());
			LOGGER.info("Housing Data:" + data.toString());
			repository.save(housingData);
			
		} catch(Exception e) {
			e.printStackTrace();
			return false;
		}
		return false;
	}
	
}
