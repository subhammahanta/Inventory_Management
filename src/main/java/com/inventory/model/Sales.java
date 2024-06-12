package com.inventory.model;

import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Entity
public class Sales {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int salesId;


    private Double totalAmount;

    private String customerName;

    private String phoneNumber;

    private String description;

  
     private String orderId;


     

    
    private Date saleDate;


    @Column(name = "soldBy")
    private int soldBy;

 
    public String getCustomerName() {
        return customerName;
    }


    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }


    public String getPhoneNumber() {
        return phoneNumber;
    }


    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }


    public String getDescription() {
        return description;
    }


    public void setDescription(String description) {
        this.description = description;
    }


 
    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "saleId")
     private List<SaleDetails> saleDetails;






   

    public Sales(Double totalAmount, String customerName, String phoneNumber, String description, String orderId,
            Date saleDate, int soldBy, List<SaleDetails> saleDetails) {
        this.totalAmount = totalAmount;
        this.customerName = customerName;
        this.phoneNumber = phoneNumber;
        this.description = description;
        this.orderId = orderId;
        this.saleDate = saleDate;
        this.soldBy = soldBy;
        this.saleDetails = saleDetails;
    }


    public Sales() {
    }


    public int getSalesId() {
        return salesId;
    }


    public void setSalesId(int salesId) {
        this.salesId = salesId;
    }


    public Double getTotalAmount() {
        return totalAmount;
    }


    public void setTotalAmount(Double totalAmount) {
        this.totalAmount = totalAmount;
    }


    public Date getSaleDate() {
        return saleDate;
    }


    public void setSaleDate(Date saleDate) {
        this.saleDate = saleDate;
    }


    public List<SaleDetails> getSaleDetails() {
        return saleDetails;
    }


    public void setSaleDetails(List<SaleDetails> saleDetails) {
        this.saleDetails = saleDetails;
    }


    public int getSoldBy() {
        return soldBy;
    }


    public void setSoldBy(int soldBy) {
        this.soldBy = soldBy;
    }


    @Override
    public String toString() {
        return "Sales [salesId=" + salesId + ", totalAmount=" + totalAmount + ", customerName=" + customerName
                + ", phoneNumber=" + phoneNumber + ", description=" + description + ", saleDate=" + saleDate
                + ", soldBy=" + soldBy + ", saleDetails=" + saleDetails + "]";
    }


 

    public String getOrderId() {
        return orderId;
    }


    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }


    
}
