package com.inventory.dto;

import javax.persistence.Column;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

public class ProductDto {

//@Null(message = "Product ID can be null")
    private Integer productId;

     @NotBlank (message = "Product name is mandatory")
    private String productName;

     @NotBlank(message = "Product type is mandatory")
    private String productType;

    //  @NotNull(message = "Quantity is mandatory")
    //  @Min(value = 0, message = "Quantity must be greater than or equal to 0")
    private Integer quantity;

    //  @NotNull(message = "Minimum quantity is mandatory")
    //  @Min(value = 0, message = "Minimum quantity must be greater than or equal to 0")
    private Integer minimumQuantity;

    //  @NotNull(message = "Cost price is mandatory")
    //  @DecimalMin(value = "0.0", inclusive = false, message = "Cost price must be greater than 0")
    private Double costPrice;

    //  @NotNull(message = "Selling price is mandatory")
    //  @DecimalMin(value = "0.0", inclusive = false, message = "Selling price must be greater than 0")
    private Double sellingPrice;

    // @NotNull(message = "Active status is mandatory")
     private Boolean isActive;
     

     public ProductDto(Integer productId, @NotBlank(message = "Product name is mandatory") String productName,
            @NotBlank(message = "Product type is mandatory") String productType,
            @NotNull(message = "Quantity is mandatory") @Min(value = 0, message = "Quantity must be greater than or equal to 0") Integer quantity,
            @NotNull(message = "Minimum quantity is mandatory") @Min(value = 0, message = "Minimum quantity must be greater than or equal to 0") Integer minimumQuantity,
            @NotNull(message = "Cost price is mandatory") @DecimalMin(value = "0.0", inclusive = false, message = "Cost price must be greater than 0") Double costPrice,
            @NotNull(message = "Selling price is mandatory") @DecimalMin(value = "0.0", inclusive = false, message = "Selling price must be greater than 0") Double sellingPrice,
            Boolean isActive, @NotNull(message = "Admin ID is mandatory") Integer adminId) {
        this.productId = productId;
        this.productName = productName;
        this.productType = productType;
        this.quantity = quantity;
        this.minimumQuantity = minimumQuantity;
        this.costPrice = costPrice;
        this.sellingPrice = sellingPrice;
        this.isActive = isActive;
        this.adminId = adminId;
    }

    public ProductDto() {
    }

    @NotNull(message = "Admin ID is mandatory")
    private Integer adminId;

    public Integer getProductId() {
        return productId;
    }

    public void setProductId(Integer productId) {
        this.productId = productId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getProductType() {
        return productType;
    }

    public void setProductType(String productType) {
        this.productType = productType;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Integer getMinimumQuantity() {
        return minimumQuantity;
    }

    public void setMinimumQuantity(Integer minimumQuantity) {
        this.minimumQuantity = minimumQuantity;
    }

    public Double getCostPrice() {
        return costPrice;
    }

    public void setCostPrice(Double costPrice) {
        this.costPrice = costPrice;
    }

    public Double getSellingPrice() {
        return sellingPrice;
    }

    public void setSellingPrice(Double sellingPrice) {
        this.sellingPrice = sellingPrice;
    }

    public Boolean getIsActive() {
        return isActive;
    }

    public void setIsActive(Boolean isActive) {
        this.isActive = isActive;
    }

    public Integer getAdminId() {
        return adminId;
    }

    public void setAdminId(Integer adminId) {
        this.adminId = adminId;
    }




}
