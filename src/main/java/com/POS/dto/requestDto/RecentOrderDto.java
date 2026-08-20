package com.POS.dto.requestDto;


public class RecentOrderDto {

    private String orderId;
    private String customer;
    private Double total;
    private String status;

    public RecentOrderDto() {
    }

    public RecentOrderDto(String orderId, String customer, Double total, String status) {
        this.orderId = orderId;
        this.customer = customer;
        this.total = total;
        this.status = status;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public String getCustomer() {
        return customer;
    }

    public void setCustomer(String customer) {
        this.customer = customer;
    }

    public Double getTotal() {
        return total;
    }

    public void setTotal(Double total) {
        this.total = total;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
