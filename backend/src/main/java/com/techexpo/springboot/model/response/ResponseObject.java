package com.techexpo.springboot.model.response;

import java.util.List;

public class ResponseObject {

	List <?> objectList ;

	public List<?> getObjectList() {
		return objectList;
	}

	public void setObjectList(List<?> objectList) {
		this.objectList = objectList;
	}

	@Override
	public String toString() {
		return "ResponseObject [objectList=" + objectList + "]";
	}

		
	
}
