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
import com.techexpo.springboot.model.common.HousingDataWrapper;
import com.techexpo.springboot.model.response.ResponseObject;
import com.techexpo.springboot.service.HousingDataService;

/**
 * @author Sravan
 *
 */

@RestController
@RequestMapping("/housing")
public class HousingController {
	
    private static final Logger LOGGER = LoggerFactory.getLogger(HousingController.class);

    @Autowired
    StringRedisTemplate redisTemplate;
    
    @Autowired
    private RedisAtomicInteger atomicInteger; 
    
    private HousingDataService housingDataService;
    
    @Autowired
    HousingController(HousingDataService housingDataService) {
        this.housingDataService = housingDataService;
    }
    
    @RequestMapping(method = RequestMethod.GET)
    public ResponseObject getAllHousingData(){
      LOGGER.info("Getting list of housing data");
      atomicInteger.incrementAndGet();
      ResponseObject responseObject = housingDataService.getHousingData();
	  LOGGER.info(responseObject.toString());
      return responseObject;
    }
    
	
 	@RequestMapping(method = RequestMethod.POST)
 	public List<String> saveHousingData(@RequestBody HousingDataWrapper housingDataWrapper){
 		LOGGER.info("Saving Housing Data Object with wrapper....");
 		List<String> responseHousingData = new ArrayList<String>();
 		List<HousingData> housingDataList = housingDataWrapper.getHousingDatas(); 
 		LOGGER.info("housingDataList size...."+housingDataList);
 		for(HousingData housingData: housingDataList ) {
 			LOGGER.info("Saving HousingData [housingData]...."+housingData.toString());
 			HousingData housingDataObject = housingDataService.saveHousingData(housingData);
 			responseHousingData.add("Saved HousingData: " + housingDataObject.toString());
 		}
	    return responseHousingData;
 	}
 	
 	@RequestMapping(method = RequestMethod.PUT, value="/update")
 	public boolean updateeHousingData(@RequestBody HousingData housingData){
 		LOGGER.info("Updating Housing Data Object for ...." + housingData.getId());
 		housingDataService.updateHousingData(housingData);
 		LOGGER.info("updated Housing Data Object for ...." + housingData.getId());
	    return true;
 	}
 	
 	
 	@RequestMapping(method = RequestMethod.DELETE, value="/delete")
 	public boolean deleteHousingData(@RequestParam String id){
 		LOGGER.info("Deleting Housing Data Object for ...." + id);
 		housingDataService.deleteHousingData(id);
 		LOGGER.info("Deleted Housing Data Object for ...." + id);
	    return true;
 	}
 	
 	@RequestMapping(method = RequestMethod.GET, value="/findById")
 	public HousingData getHousingData(@RequestParam String id){
 		LOGGER.info("finding Housing Data Object for ...." + id);
 		HousingData data = housingDataService.findHousingData(id);
 		LOGGER.info("found Housing Data Object for ...." + id);
	    return data;
 	}
	
	@RequestMapping(method=RequestMethod.GET, value="/count")
	public long count () {
		LOGGER.info("Getting counter....." + atomicInteger.get());
		return atomicInteger.get();
	}
	
}
