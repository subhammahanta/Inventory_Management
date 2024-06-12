package com.inventory.dto;

import java.util.Date;
import java.util.List;



public class InvoiceDto {


    private String customerName;


    private String phoneNo;

    private String description;


    private Double totalAmount;


    private Date date;

  
    private int soldBy;


    private String orderId;

     
    private int totalBills;


    public int getTotalBills() {
        return totalBills;
    }

    public void setTotalBills(int totalBills) {
        this.totalBills = totalBills;
    }
  

    public InvoiceDto() {
    }


    List<ProductInvoiceApiResponseDto> products;


    public String getCustomerName() {
        return customerName;
    }


    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }


    @Override
    public String toString() {
        return "InvoiceDto [customerName=" + customerName + ", phoneNo=" + phoneNo + ", description=" + description
                + ", totalAmount=" + totalAmount + ", date=" + date + ", soldBy=" + soldBy + ", products=" + products
                + "]";
    }


    public String getPhoneNo() {
        return phoneNo;
    }


    public void setPhoneNo(String phoneNo) {
        this.phoneNo = phoneNo;
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


    public List<ProductInvoiceApiResponseDto> getProducts() {
        return products;
    }


    public void setProducts(List<ProductInvoiceApiResponseDto> products) {
        this.products = products;
    }


    public Date getDate() {
        return date;
    }


    public void setDate(Date date) {
        this.date = date;
    }


    public int getSoldBy() {
        return soldBy;
    }


    public void setSoldBy(int soldBy) {
        this.soldBy = soldBy;
    }


    public InvoiceDto(String customerName, String phoneNo, String description, Double totalAmount, Date date,
            int soldBy, List<ProductInvoiceApiResponseDto> products) {
        this.customerName = customerName;
        this.phoneNo = phoneNo;
        this.description = description;
        this.totalAmount = totalAmount;
        this.date = date;
        this.soldBy = soldBy;
        this.products = products;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }


 
    

}
