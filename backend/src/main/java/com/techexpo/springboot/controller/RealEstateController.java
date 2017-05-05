package com.techexpo.springboot.controller;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.support.atomic.RedisAtomicInteger;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

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
      //atomicInteger.incrementAndGet();
      ResponseObject responseObject = realEstateDataService.getAllRealEstateData();
	  LOGGER.info(responseObject.toString());
      return responseObject;
    }
    
	
 	@RequestMapping(method = RequestMethod.POST)
 	public List<String> saveRealEstateData(@RequestBody RealEstateDataRequest realEstateDataRequest){
 		LOGGER.info("Saving RealEstateData Object with wrapper....");
 		List<String> responseRealEsateData = new ArrayList<String>();
 		List<RealEstateData> realEstateDataList = realEstateDataRequest.getRealEstateDatas(); 
 		LOGGER.info("realEstateDataList size...."+realEstateDataList);
 		for(RealEstateData realEstateData: realEstateDataList ) {
 			LOGGER.info("Saving RealEsateData ...."+realEstateData.toString());
 			RealEstateData realEstateDataObject = realEstateDataService.saveRealEstate(realEstateData);
 			responseRealEsateData.add("Saved RealEsateData: " + realEstateDataObject.toString());
 		}
	    return responseRealEsateData;
 	}
 	
 	@RequestMapping(method = RequestMethod.PUT)
 	public boolean updateRealEstateData(@RequestBody RealEstateData realEstateData){
 		LOGGER.info("Updating RealEstateData Object for ...." + realEstateData.getRealEstateId());
 		realEstateDataService.updateRealEstate(realEstateData);
 		LOGGER.info("updated RealEstateData Object for ...." + realEstateData.getRealEstateId());
	    return true;
 	}
 	
 	
 	@RequestMapping(method = RequestMethod.DELETE, value="/{realEstateId}")
 	public boolean deleteRealEstateData(@PathVariable("realEstateId") String realEstateId){
 		LOGGER.info("Deleting RealEstateData Object for ...." + realEstateId);
 		realEstateDataService.deleteByRealEstateId(realEstateId);
 		LOGGER.info("Deleted RealEstateData Object for ...." + realEstateId);
	    return true;
 	}
 	
 	@RequestMapping(method = RequestMethod.GET, value="/{realEstateId}")
 	public RealEstateData findRealEstateDataByRealEstateId(@PathVariable("realEstateId") String realEstateId){
 		LOGGER.info("finding RealEstateData Object for realEstateId...." + realEstateId);
 		RealEstateData data = realEstateDataService.findByRealEstateId(realEstateId);
 		LOGGER.info("found RealEstateData Object for ...." + realEstateId);
	    return data;
 	}
	
	@RequestMapping(method=RequestMethod.GET, value="/count")
	public long count () {
		LOGGER.info("Getting counter....." + atomicInteger.get());
		//return atomicInteger.get();
		return atomicInteger.incrementAndGet();
	}
	
}
