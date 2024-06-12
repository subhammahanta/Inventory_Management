package com.inventory.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

public class ProductResponseDto {

    private int productId;

    private String productName;

    private String productType;

    private int quantity;

    private int minimumQuantity;

    private Double costPrice;

    private Double sellingPrice;

    private String productCode;

    private Integer adminId;

    private Boolean isActive;
    

    @Override
    public String toString() {
        return "ProductResponseDto [productId=" + productId + ", productName=" + productName + ", productType="
                + productType + ", quantity=" + quantity + ", minimumQuantity=" + minimumQuantity + ", costPrice="
                + costPrice + ", sellingPrice=" + sellingPrice + ", productCode=" + productCode + ", adminId=" + adminId
                + ", isActive=" + isActive + "]";
    }

    public ProductResponseDto(int productId, String productName, String productType, int quantity, int minimumQuantity,
            Double costPrice, Double sellingPrice, String productCode, Integer adminId, Boolean isActive) {
        this.productId = productId;
        this.productName = productName;
        this.productType = productType;
        this.quantity = quantity;
        this.minimumQuantity = minimumQuantity;
        this.costPrice = costPrice;
        this.sellingPrice = sellingPrice;
        this.productCode = productCode;
        this.adminId = adminId;
        this.isActive = isActive;
    }

    public ProductResponseDto() {
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

    public String getProductType() {
        return productType;
    }

    public void setProductType(String productType) {
        this.productType = productType;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public int getMinimumQuantity() {
        return minimumQuantity;
    }

    public void setMinimumQuantity(int minimumQuantity) {
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

    public String getProductCode() {
        return productCode;
    }

    public void setProductCode(String productCode) {
        this.productCode = productCode;
    }

    public Integer getAdminId() {
        return adminId;
    }

    public void setAdminId(Integer adminId) {
        this.adminId = adminId;
    }

    public Boolean getIsActive() {
        return isActive;
    }

    public void setIsActive(Boolean isActive) {
        this.isActive = isActive;
    }

}
