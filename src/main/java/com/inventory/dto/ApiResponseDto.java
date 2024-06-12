package com.inventory.dto;




public class ApiResponseDto {

   private String message;

   private boolean success;

   

   public ApiResponseDto() {
   }

   public String getMessage() {
      return message;
   }

   public void setMessage(String message) {
      this.message = message;
   }

   public boolean isSuccess() {
      return success;
   }

   public void setSuccess(boolean success) {
      this.success = success;
   }

   public ApiResponseDto(String message, boolean success) {
      this.message = message;
      this.success = success;
   }


   

}
