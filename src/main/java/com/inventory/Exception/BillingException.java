package com.inventory.Exception;

public class BillingException extends RuntimeException{

  private String message;

public BillingException(String message) {
    this.message = message;
}


public String getMessage() {
    return message;
}

public void setMessage(String message) {
    this.message = message;
}

public BillingException() {
     super();
}

  

}
