package com.inventory.Exception;

public class NoDataFoundException extends RuntimeException {

  private String message;

public NoDataFoundException(String message) {
    super(message);
    this.message=message;
}

public String getMessage() {
    return message;
}

public void setMessage(String message) {
    this.message = message;
}
public NoDataFoundException() {
    super();
  
}

}
