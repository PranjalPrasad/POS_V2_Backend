package com.POS.dto.requestDto;

public class TopSellingProductDto {

    private String productId;
    private String productName;
    private Integer unitsSold;

    public TopSellingProductDto() {
    }

    public TopSellingProductDto(String productId, String productName, Integer unitsSold) {
        this.productId = productId;
        this.productName = productName;
        this.unitsSold = unitsSold;
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public Integer getUnitsSold() {
        return unitsSold;
    }

    public void setUnitsSold(Integer unitsSold) {
        this.unitsSold = unitsSold;
    }
}
