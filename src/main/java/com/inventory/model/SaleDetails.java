package com.inventory.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class SaleDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
 private int saleDetailsId;

 private String productCode;

 private int quantity;


 private String productName;


 private Double totalPrice;

 private Double sellingPrice;

 
   




public Double getTotalPrice() {
    return totalPrice;
}

public void setTotalPrice(Double totalPrice) {
    this.totalPrice = totalPrice;
}

@Override
public String toString() {
    return "SaleDetails [saleDetailsId=" + saleDetailsId + ", productCode=" + productCode + ", quantity=" + quantity
            + ", productName=" + productName + ", getSaleDetailsId()=" + getSaleDetailsId() + ", getProductCode()="
            + getProductCode() + ", getQuantity()=" + getQuantity() + ", getProductName()=" + getProductName()
            + ", getClass()=" + getClass() + ", hashCode()=" + hashCode() + ", toString()=" + super.toString() + "]";
}

public SaleDetails(String productCode, int quantity, String productName) {
    this.productCode = productCode;
    this.quantity = quantity;
    this.productName = productName;
}

public SaleDetails() {
}

public int getSaleDetailsId() {
    return saleDetailsId;
}

public void setSaleDetailsId(int saleDetailsId) {
    this.saleDetailsId = saleDetailsId;
}

public String getProductCode() {
    return productCode;
}

public void setProductCode(String productCode) {
    this.productCode = productCode;
}

public int getQuantity() {
    return quantity;
}

public void setQuantity(int quantity) {
    this.quantity = quantity;
}

public String getProductName() {
    return productName;
}

public void setProductName(String productName) {
    this.productName = productName;
}

public Double getSellingPrice() {
    return sellingPrice;
}

public void setSellingPrice(Double sellingPrice) {
    this.sellingPrice = sellingPrice;
}

 



}
