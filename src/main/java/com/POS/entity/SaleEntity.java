package com.POS.entity;


import jakarta.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "sales")
public class SaleEntity {

    @Id
    @Column(name = "sale_id")
    private String saleId;

    @Column(name = "sale_number", unique = true)
    private String saleNumber;

    @Column(name = "tenant_id")
    private String tenantId;

    @Column(name = "branch_id")
    private String branchId;

    // ---- Customer relation ----
    @ManyToOne
    @JoinColumn(name = "customer_id")
    private CustomerEntity customer;

    // ---- Order info (flattened) ----
    @Column(name = "order_id")
    private String orderId;

    @Column(name = "order_type")
    private String orderType;

    @Column(name = "order_source")
    private String orderSource;

    // ---- Items ----
    @OneToMany(mappedBy = "sale", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<SaleItemEntity> items = new ArrayList<>();

    // ---- Pricing (flattened) ----
    @Column(name = "subtotal")
    private Double subtotal;

    @Column(name = "discount_amount")
    private Double discountAmount;

    @Column(name = "tax_amount")
    private Double taxAmount;

    @Column(name = "round_off")
    private Double roundOff;

    @Column(name = "grand_total")
    private Double grandTotal;

    // ---- Payment (flattened) ----
    @Column(name = "payment_status")
    private String paymentStatus;

    @Column(name = "paid_amount")
    private Double paidAmount;

    @Column(name = "due_amount")
    private Double dueAmount;

    @Column(name = "payment_method")
    private String paymentMethod;

    @Column(name = "transaction_reference")
    private String transactionReference;

    // ---- Status / meta ----
    @Column(name = "status")
    private String status;

    @Column(name = "notes")
    private String notes;

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    public SaleEntity() {}

    // ---- Getters / Setters ----
    public String getSaleId() { return saleId; }
    public void setSaleId(String saleId) { this.saleId = saleId; }

    public String getSaleNumber() { return saleNumber; }
    public void setSaleNumber(String saleNumber) { this.saleNumber = saleNumber; }

    public String getTenantId() { return tenantId; }
    public void setTenantId(String tenantId) { this.tenantId = tenantId; }

    public String getBranchId() { return branchId; }
    public void setBranchId(String branchId) { this.branchId = branchId; }

    public CustomerEntity getCustomer() { return customer; }
    public void setCustomer(CustomerEntity customer) { this.customer = customer; }

    public String getOrderId() { return orderId; }
    public void setOrderId(String orderId) { this.orderId = orderId; }

    public String getOrderType() { return orderType; }
    public void setOrderType(String orderType) { this.orderType = orderType; }

    public String getOrderSource() { return orderSource; }
    public void setOrderSource(String orderSource) { this.orderSource = orderSource; }

    public List<SaleItemEntity> getItems() { return items; }
    public void setItems(List<SaleItemEntity> items) { this.items = items; }

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

    public LocalDateTime getCreatedAt() { return createdAt; }
    public void setCreatedAt(LocalDateTime createdAt) { this.createdAt = createdAt; }

    public LocalDateTime getUpdatedAt() { return updatedAt; }
    public void setUpdatedAt(LocalDateTime updatedAt) { this.updatedAt = updatedAt; }
}
