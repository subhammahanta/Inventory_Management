package com.inventory.Exception;

import org.springframework.stereotype.Component;

@Component
public class ResourseNotFoundException extends RuntimeException{


    String resourceName;
	 long fieldName;
	public ResourseNotFoundException(String resourceName, long fieldName) {
		this.resourceName = resourceName;
		this.fieldName = fieldName;
		System.out.println( resourceName+" Resourse Not Found with fieldName "+fieldName);
	}
	public ResourseNotFoundException() {
	}
	
	

	 

}
