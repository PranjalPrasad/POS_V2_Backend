package com.POS.dto.requestDto;

import java.util.List;

public class SalePatchRequestDto {

    private String saleNumber;
    private String branchId;

    // customer (flattened)
    private String customerName;
    private String customerPhone;

    // order (flattened)
    private String orderType;
    private String orderSource;

    // items (agar bheja to poore items list replace ho jayegi)
    private List<SaleItemRequestDto> items;

    // pricing (flattened)
    private Double subtotal;
    private Double discountAmount;
    private Double taxAmount;
    private Double roundOff;
    private Double grandTotal;

    // payment (flattened)
    private String paymentStatus;
    private Double paidAmount;
    private Double dueAmount;
    private String paymentMethod;
    private String transactionReference;

    private String status;
    private String notes;

    public SalePatchRequestDto() {}

    public String getSaleNumber() { return saleNumber; }
    public void setSaleNumber(String saleNumber) { this.saleNumber = saleNumber; }

    public String getBranchId() { return branchId; }
    public void setBranchId(String branchId) { this.branchId = branchId; }

    public String getCustomerName() { return customerName; }
    public void setCustomerName(String customerName) { this.customerName = customerName; }

    public String getCustomerPhone() { return customerPhone; }
    public void setCustomerPhone(String customerPhone) { this.customerPhone = customerPhone; }

    public String getOrderType() { return orderType; }
    public void setOrderType(String orderType) { this.orderType = orderType; }

    public String getOrderSource() { return orderSource; }
    public void setOrderSource(String orderSource) { this.orderSource = orderSource; }

    public List<SaleItemRequestDto> getItems() { return items; }
    public void setItems(List<SaleItemRequestDto> items) { this.items = items; }

    public Double getSubtotal() { return subtotal; }
    public void setSubtotal(Double subtotal) { this.subtotal = subtotal; }

    public Double getDiscountAmount() { return discountAmount; }
    public void setDiscountAmount(Double discountAmount) { this.discountAmount = discountAmount; }

    public Double getTaxAmount() { return taxAmount; }
    public void setTaxAmount(Double taxAmount) { this.taxAmount = taxAmount; }

    public Double getRoundOff() { return roundOff; }
    public void setRoundOff(Double roundOff) { this.roundOff = roundOff; }

    public Double getGrandTotal() { return grandTotal; }
    public void setGrandTotal(Double grandTotal) { this.grandTotal = grandTotal; }

    public String getPaymentStatus() { return paymentStatus; }
    public void setPaymentStatus(String paymentStatus) { this.paymentStatus = paymentStatus; }

    public Double getPaidAmount() { return paidAmount; }
    public void setPaidAmount(Double paidAmount) { this.paidAmount = paidAmount; }

    public Double getDueAmount() { return dueAmount; }
    public void setDueAmount(Double dueAmount) { this.dueAmount = dueAmount; }

    public String getPaymentMethod() { return paymentMethod; }
    public void setPaymentMethod(String paymentMethod) { this.paymentMethod = paymentMethod; }

    public String getTransactionReference() { return transactionReference; }
    public void setTransactionReference(String transactionReference) { this.transactionReference = transactionReference; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }

    public String getNotes() { return notes; }
    public void setNotes(String notes) { this.notes = notes; }
}
