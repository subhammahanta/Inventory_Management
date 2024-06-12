package com.inventory.dto;

import java.util.List;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class ProductSaleDto {

    // @NotBlank(message = "adminId cannot be blank")
    @NotNull(message = "adminId cannot be null")
    private Integer adminId;

    @NotBlank(message = "Customer cannot be blank")
    @NotNull(message = "CustomerName cannot be null")
    private String customerName;

    @NotBlank(message = "Description cannot be blank")
    @NotNull(message = "Description cannot be null")
    private String description;

    // @NotBlank(message = "totalAmount cannot be blank")
    @NotNull(message = "totalAmount cannot be null")
    private Double totalAmount;

    @NotBlank(message = "PhoneNo cannot be blank")
    @NotNull(message = "phoneNo cannot be null")
    private String phoneNo;

    List<ProductSaleDetailsDto> products;

    @Override
    public String toString() {
        return "ProductSaleDto [userId=" + adminId + ", products=" + products + ", getClass()=" + getClass()
                + ", hashCode()=" + hashCode() + ", toString()=" + super.toString() + "]";

    }

    public ProductSaleDto(
            @NotBlank(message = "Customer cannot be blank") @NotNull(message = "CustomerName cannot be null") String customerName,
            @NotBlank(message = "Description cannot be blank") @NotNull(message = "Description cannot be null") String description,
            @NotBlank(message = "totalAmount cannot be blank") @NotNull(message = "totalAmount cannot be null") Double totalAmount,
            @NotBlank(message = "PhoneNo cannot be blank") @NotNull(message = "phoneNo cannot be null") String phoneNo,
            List<ProductSaleDetailsDto> products) {
        this.customerName = customerName;
        this.description = description;
        this.totalAmount = totalAmount;
        this.phoneNo = phoneNo;
        this.products = products;
    }

    public ProductSaleDto() {
    }

    public int getAdminId() {
        return adminId;
    }

    public void setAdminId(int adminId) {
        this.adminId = adminId;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Double getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(Double totalAmount) {
        this.totalAmount = totalAmount;
    }

    public String getPhoneNo() {
        return phoneNo;
    }

    public void setPhoneNo(String phoneNo) {
        this.phoneNo = phoneNo;
    }

    public List<ProductSaleDetailsDto> getProducts() {
        return products;
    }

    public void setProducts(List<ProductSaleDetailsDto> products) {
        this.products = products;
    }

}
