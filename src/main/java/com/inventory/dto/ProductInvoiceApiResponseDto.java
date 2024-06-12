package com.inventory.dto;

public class ProductInvoiceApiResponseDto {

    private String productCode;

   private int quantity;

    private Double totalPrice;

    private Double sellingPrice;

    private String productName;

 

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

    public ProductInvoiceApiResponseDto() {
    }

    public ProductInvoiceApiResponseDto(String productCode, int quantity, String productName) {
        this.productCode = productCode;
        this.quantity = quantity;
        this.productName = productName;
    }

    public Double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(Double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public Double getSellingPrice() {
        return sellingPrice;
    }

    public void setSellingPrice(Double sellingPrice) {
        this.sellingPrice = sellingPrice;
    }

}
