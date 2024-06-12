package com.inventory.Exception;
import java.sql.SQLIntegrityConstraintViolationException;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.inventory.dto.ApiResponseDto;

import io.jsonwebtoken.ExpiredJwtException;

@RestControllerAdvice
public class GlobalException {

@ExceptionHandler(ResourseNotFoundException.class)
	public ResponseEntity<ApiResponseDto> resourceNotFoundExceptionHandler(ResourseNotFoundException ex) {
		String message = ex.getMessage();
		ApiResponseDto apiResponse = new ApiResponseDto(message, false);
		return new ResponseEntity<ApiResponseDto>(apiResponse, HttpStatus.NOT_FOUND);
	}

@ExceptionHandler(SQLIntegrityConstraintViolationException.class)
	public ResponseEntity<ApiResponseDto> userAlreadyExists(SQLIntegrityConstraintViolationException ex) {
		String message = "User already exists. Please login";
		ApiResponseDto apiResponse = new ApiResponseDto(message, false);
		return new ResponseEntity<ApiResponseDto>(apiResponse, HttpStatus.CONFLICT);
	}

   @ExceptionHandler(InternalAuthenticationServiceException.class)
   public ResponseEntity<ApiResponseDto> userDoesnotExits(InternalAuthenticationServiceException ex){
	String message = "User does not exists. Please SignIn";
	ApiResponseDto apiResponse = new ApiResponseDto(message, false);
	return new ResponseEntity<ApiResponseDto>(apiResponse, HttpStatus.NOT_FOUND);
   }

   @ExceptionHandler(BadCredentialsException.class)
   public ResponseEntity<ApiResponseDto> userDoesnotExits(BadCredentialsException ex){
	String message = "Not a valid Username or Password";
	ApiResponseDto apiResponse = new ApiResponseDto(message, false);
	return new ResponseEntity<ApiResponseDto>(apiResponse, HttpStatus.NOT_FOUND);
   }

   @ExceptionHandler( io.jsonwebtoken.ExpiredJwtException.class)
   public ResponseEntity<ApiResponseDto> userDoesnotExits(ExpiredJwtException ex){
	String message ="Session expired..!!";
	ApiResponseDto apiResponse = new ApiResponseDto(message, false);
	return new ResponseEntity<ApiResponseDto>(apiResponse, HttpStatus.FORBIDDEN);
   }

  
   @ExceptionHandler( NoDataFoundException.class)
   public ResponseEntity<ApiResponseDto> noDataFoundException(NoDataFoundException ex){
	String message = ex.getMessage();
	ApiResponseDto apiResponse = new ApiResponseDto(message, false);
	return new ResponseEntity<ApiResponseDto>(apiResponse, HttpStatus.BAD_REQUEST);
   }

  
   @ExceptionHandler( BillingException.class)
   public ResponseEntity<ApiResponseDto> noDataFoundException(BillingException ex){
	String message = ex.getMessage();
	ApiResponseDto apiResponse = new ApiResponseDto(message, false);
	return new ResponseEntity<ApiResponseDto>(apiResponse, HttpStatus.BAD_REQUEST);
   }

   @ExceptionHandler( ProductNameAlreadyExistException.class)
   public ResponseEntity<ApiResponseDto> productNameAlreadyExists(ProductNameAlreadyExistException ex){
	String message = "Product Already Exists!!";
	ApiResponseDto apiResponse = new ApiResponseDto(message, false);
	return new ResponseEntity<ApiResponseDto>(apiResponse, HttpStatus.BAD_REQUEST);
   }

//    @ExceptionHandler(Exception.class)
//    public ResponseEntity<ApiResponseDto> handleAllExceptions(Exception ex) {
// 	   System.out.println("Caught exception: " + ex.getClass().getName());
// 	   ApiResponseDto apiResponse = new ApiResponseDto("Internal Server Error", false);
// 	   return new ResponseEntity<>(apiResponse, HttpStatus.FORBIDDEN);
//    }

  

}
