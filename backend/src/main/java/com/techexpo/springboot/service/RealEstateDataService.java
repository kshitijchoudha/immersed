/**
 * 
 */
package com.techexpo.springboot.service;

import com.techexpo.springboot.model.common.RealEstateData;
import com.techexpo.springboot.model.response.ResponseObject;

/**
 * @author Madhu
 *
 */
public interface RealEstateDataService {

	public ResponseObject getData();
	
	public RealEstateData saveData(RealEstateData housingData);
	
	public boolean updateData(RealEstateData housingData);
	
	public boolean deleteDataById(String id);
	
	public RealEstateData findDataById(String id);
}
