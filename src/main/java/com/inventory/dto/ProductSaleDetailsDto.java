package com.inventory.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class ProductSaleDetailsDto {

     @NotNull(message = "prdouctId is required when placing an order")
     // @NotBlank(message = "productId cannot be Blank while placing an order")
     private Integer productId;

     @NotNull(message = "prdouctName is required when placing an order")
     @NotBlank(message = "productName cannot be Blank while placing an order")
     private String productName;

     @NotNull(message = "prdouctQuantity is required when placing an order")
     // @NotBlank(message = "productQuantity cannot be Blank while placing an order")
     private Integer quantity;

     @NotNull(message = "sellingPrice is required when placing an order")
     // @NotBlank(message = "sellingPrice cannot be Blank while placing an order")
     private Double sellingPrice;

     @NotNull(message = "totalPrice is required when placing an order")
     // @NotBlank(message = "totalPrice cannot be Blank while placing an order")
     private Double totalPrice;

     @NotNull(message = "productCode is required when placing an order")
     @NotBlank(message = "productCode cannot be Blank while placing an order")
     private String productCode;

     public ProductSaleDetailsDto(
               @NotNull(message = "prdouctId is required when placing an order") @NotBlank(message = "productId cannot be Blank while placing an order") int productId,
               @NotNull(message = "prdouctName is required when placing an order") @NotBlank(message = "productName cannot be Blank while placing an order") String productName,
               @NotNull(message = "prdouctQuantity is required when placing an order") @NotBlank(message = "productQuantity cannot be Blank while placing an order") int quantity,
               @NotNull(message = "sellingPrice is required when placing an order") @NotBlank(message = "sellingPrice cannot be Blank while placing an order") Double sellingPrice,
               @NotNull(message = "totalPrice is required when placing an order") @NotBlank(message = "totalPrice cannot be Blank while placing an order") Double totalPrice,
               @NotNull(message = "productCode is required when placing an order") @NotBlank(message = "productCode cannot be Blank while placing an order") String productCode) {
          this.productId = productId;
          this.productName = productName;
          this.quantity = quantity;
          this.sellingPrice = sellingPrice;
          this.totalPrice = totalPrice;
          this.productCode = productCode;
     }

     public ProductSaleDetailsDto() {
     }

     public int getProductId() {
          return productId;
     }

     public void setProductId(int productId) {
          this.productId = productId;
     }

     public String getProductName() {
          return productName;
     }

     public void setProductName(String productName) {
          this.productName = productName;
     }

     public int getQuantity() {
          return quantity;
     }

     public void setQuantity(int quantity) {
          this.quantity = quantity;
     }

     public Double getSellingPrice() {
          return sellingPrice;
     }

     public void setSellingPrice(Double sellingPrice) {
          this.sellingPrice = sellingPrice;
     }

     public Double getTotalPrice() {
          return totalPrice;
     }

     public void setTotalPrice(Double totalPrice) {
          this.totalPrice = totalPrice;
     }

     public String getProductCode() {
          return productCode;
     }

     public void setProductCode(String productCode) {
          this.productCode = productCode;
     }

}
