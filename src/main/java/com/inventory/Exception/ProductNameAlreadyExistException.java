package com.inventory.Exception;

public class ProductNameAlreadyExistException extends RuntimeException {

  String resourceName;
	 long fieldName;
	public ProductNameAlreadyExistException(String resourceName, long fieldName) {
		this.resourceName = resourceName;
		this.fieldName = fieldName;
		System.out.println( resourceName+" Resourse Not Found with fieldName "+fieldName);
	}
	public ProductNameAlreadyExistException() {
	}
	
}
