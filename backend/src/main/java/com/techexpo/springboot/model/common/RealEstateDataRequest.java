/**
 * 
 */
package com.techexpo.springboot.model.common;

import java.util.List;

/**
 * @author Madhu
 *
 */
public class RealEstateDataRequest {
	private List<RealEstateData> realEstateDatas;

	public List<RealEstateData> getRealEstateDatas() {
		return realEstateDatas;
	}

	public void setRealEstateDatas(List<RealEstateData> realEstateDatas) {
		this.realEstateDatas = realEstateDatas;
	}

	@Override
	public String toString() {
		return "RealEstateDataRequest [realEstateDatas=" + realEstateDatas + "]";
	}

	
}
