package com.inventory.model;

import java.time.Instant;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class ProductInventory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Integer productId;

  @Column(unique = true,nullable = false)
  private String productCode;

  


//   @OneToOne
//   @JoinColumn(name = "createdBy")
//   private Admin admin;

@Column(unique = true)
  private String productName;

  private String productType;

  private int quantity;

  private int minQuantity;


private int createdBy;

  private Date createdDate;

  private int updatedBy;

  private Date updatedDate;

  private Double costPrice;

  private Double sellingPrice;

  private Boolean isActive;

  

  






public ProductInventory(String productCode, String productName, String productType, int quantity, int minQuantity,
        int createdBy, Date createdDate, int updatedBy, Date updatedDate, Double costPrice, Double sellingPrice,
        Boolean isActive) {
    this.productCode = productCode;
    this.productName = productName;
    this.productType = productType;
    this.quantity = quantity;
    this.minQuantity = minQuantity;
    this.createdBy = createdBy;
    this.createdDate = createdDate;
    this.updatedBy = updatedBy;
    this.updatedDate = updatedDate;
    this.costPrice = costPrice;
    this.sellingPrice = sellingPrice;
    this.isActive = isActive;
}

public Date getCreatedDate() {
    return createdDate;
}

public void setCreatedDate(Date createdDate) {
    this.createdDate = createdDate;
}

public Date getUpdatedDate() {
    return updatedDate;
}

public void setUpdatedDate(Date updatedDate) {
    this.updatedDate = updatedDate;
}

public ProductInventory() {
}

public Integer getProductId() {
    return productId;
}

public void setProductId(int productId) {
    this.productId = productId;
}

public String getProductCode() {
    return productCode;
}

public void setProductCode(String productCode) {
    this.productCode = productCode;
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

public int getMinQuantity() {
    return minQuantity;
}

public void setMinQuantity(int minQuantity) {
    this.minQuantity = minQuantity;
}


public int getUpdatedBy() {
    return updatedBy;
}

public void setUpdatedBy(int updatedBy) {
    this.updatedBy = updatedBy;
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

public int getCreatedBy() {
    return createdBy;
}

public void setCreatedBy(int createdBy) {
    this.createdBy = createdBy;
}


  


}
