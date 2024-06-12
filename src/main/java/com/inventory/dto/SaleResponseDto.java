package com.inventory.dto;

public class SaleResponseDto {

    private int saleId;

    private Boolean success;

    public int getSaleId() {
        return saleId;
    }

    public void setSaleId(int saleId) {
        this.saleId = saleId;
    }

    public Boolean getSuccess() {
        return success;
    }

    public void setSuccess(Boolean success) {
        this.success = success;
    }

    public SaleResponseDto() {
    }

    public SaleResponseDto(int saleId, Boolean success) {
        this.saleId = saleId;
        this.success = success;
    }

}
