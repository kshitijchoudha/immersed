package com.techexpo.springboot.service;

import com.techexpo.springboot.model.common.HousingData;
import com.techexpo.springboot.model.response.ResponseObject;

/**
 * @author Sravan
 *
 */
public interface HousingDataService {

	public ResponseObject getHousingData();
	
	public HousingData saveHousingData(HousingData housingData);
	
	public boolean updateHousingData(HousingData housingData);
	
	public boolean deleteHousingData(String id);
	
	public HousingData findHousingData(String id);
		
}
