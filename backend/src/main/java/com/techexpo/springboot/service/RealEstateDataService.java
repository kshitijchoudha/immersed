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

	public ResponseObject getAllRealEstateData();
	
	public RealEstateData saveRealEstate(RealEstateData realEstateData);
	
	public boolean updateRealEstate(RealEstateData realEstateData);
	
	public boolean deleteByRealEstateId(String id);
	
	public RealEstateData findByRealEstateId(String id);
}
