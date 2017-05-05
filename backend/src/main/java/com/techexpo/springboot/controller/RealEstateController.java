package com.techexpo.springboot.controller;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.support.atomic.RedisAtomicInteger;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.techexpo.springboot.model.common.HousingData;
import com.techexpo.springboot.model.common.RealEstateData;
import com.techexpo.springboot.model.common.RealEstateDataRequest;
import com.techexpo.springboot.model.response.ResponseObject;
import com.techexpo.springboot.service.RealEstateDataService;

/**
 * @author Madhu
 *
 */

@RestController
@RequestMapping("/realestate")
public class RealEstateController {
	
    private static final Logger LOGGER = LoggerFactory.getLogger(RealEstateController.class);

    @Autowired
    StringRedisTemplate redisTemplate;
    
    @Autowired
    private RedisAtomicInteger atomicInteger; 
    
    
    private RealEstateDataService realEstateDataService;
    
    @Autowired
    RealEstateController(RealEstateDataService realEstateDataService) {
        this.realEstateDataService = realEstateDataService;
    }
    
    @RequestMapping(method = RequestMethod.GET)
    public ResponseObject getAllRealEstateData(){
      LOGGER.info("Getting list of real estate data");
      atomicInteger.incrementAndGet();
      ResponseObject responseObject = realEstateDataService.getData();
	  LOGGER.info(responseObject.toString());
      return responseObject;
    }
    
	
 	@RequestMapping(method = RequestMethod.POST)
 	public List<String> saveRealEstateData(@RequestBody RealEstateDataRequest realEstateDataRequest){
 		LOGGER.info("Saving Housing Data Object with wrapper....");
 		List<String> responseHousingData = new ArrayList<String>();
 		List<RealEstateData> housingDataList = realEstateDataRequest.getRealEstateDatas(); 
 		LOGGER.info("housingDataList size...."+housingDataList);
 		for(RealEstateData housingData: housingDataList ) {
 			LOGGER.info("Saving data ...."+housingData.toString());
 			RealEstateData housingDataObject = realEstateDataService.saveData(housingData);
 			responseHousingData.add("Saved HousingData: " + housingDataObject.toString());
 		}
	    return responseHousingData;
 	}
 	
 	@RequestMapping(method = RequestMethod.PUT, value="/update")
 	public boolean updateData(@RequestBody RealEstateData housingData){
 		LOGGER.info("Updating Data Object for ...." + housingData.getId());
 		realEstateDataService.updateData(housingData);
 		LOGGER.info("updated Data Object for ...." + housingData.getId());
	    return true;
 	}
 	
 	
 	@RequestMapping(method = RequestMethod.DELETE, value="/delete")
 	public boolean deleteData(@RequestParam String id){
 		LOGGER.info("Deleting Data Object for ...." + id);
 		realEstateDataService.deleteDataById(id);
 		LOGGER.info("Deleted Data Object for ...." + id);
	    return true;
 	}
 	
 	@RequestMapping(method = RequestMethod.GET, value="/findById")
 	public RealEstateData getHousingData(@RequestParam String id){
 		LOGGER.info("finding Data Object for ...." + id);
 		RealEstateData data = realEstateDataService.findDataById(id);
 		LOGGER.info("found Data Object for ...." + id);
	    return data;
 	}
	
	@RequestMapping(method=RequestMethod.GET, value="/count")
	public long count () {
		LOGGER.info("Getting counter....." + atomicInteger.get());
		return atomicInteger.get();
	}
	
}
