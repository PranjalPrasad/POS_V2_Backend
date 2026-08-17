package com.POS.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "sale_items")
public class SaleItemEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "item_id")
    private Long itemId;

    @ManyToOne
    @JoinColumn(name = "sale_id")
    private SaleEntity sale;

    @Column(name = "product_id")
    private String productId;

    @Column(name = "product_name")
    private String productName;

    @Column(name = "variant_id")
    private String variantId;

    @Column(name = "quantity")
    private Integer quantity;

    @Column(name = "unit")
    private String unit;

    @Column(name = "unit_price")
    private Double unitPrice;

    // discount (flattened)
    @Column(name = "discount_type")
    private String discountType;

    @Column(name = "discount_value")
    private Double discountValue;

    @Column(name = "discount_amount")
    private Double discountAmount;

    // tax (flattened)
    @Column(name = "tax_id")
    private String taxId;

    @Column(name = "tax_rate")
    private Double taxRate;

    @Column(name = "tax_amount")
    private Double taxAmount;

    @Column(name = "line_total")
    private Double lineTotal;

    public SaleItemEntity() {}

    public Long getItemId() { return itemId; }
    public void setItemId(Long itemId) { this.itemId = itemId; }

    public SaleEntity getSale() { return sale; }
    public void setSale(SaleEntity sale) { this.sale = sale; }

    public String getProductId() { return productId; }
    public void setProductId(String productId) { this.productId = productId; }

    public String getProductName() { return productName; }
    public void setProductName(String productName) { this.productName = productName; }

    public String getVariantId() { return variantId; }
    public void setVariantId(String variantId) { this.variantId = variantId; }

    public Integer getQuantity() { return quantity; }
    public void setQuantity(Integer quantity) { this.quantity = quantity; }

    public String getUnit() { return unit; }
    public void setUnit(String unit) { this.unit = unit; }

    public Double getUnitPrice() { return unitPrice; }
    public void setUnitPrice(Double unitPrice) { this.unitPrice = unitPrice; }

    public String getDiscountType() { return discountType; }
    public void setDiscountType(String discountType) { this.discountType = discountType; }

    public Double getDiscountValue() { return discountValue; }
    public void setDiscountValue(Double discountValue) { this.discountValue = discountValue; }

    public Double getDiscountAmount() { return discountAmount; }
    public void setDiscountAmount(Double discountAmount) { this.discountAmount = discountAmount; }

    public String getTaxId() { return taxId; }
    public void setTaxId(String taxId) { this.taxId = taxId; }

    public Double getTaxRate() { return taxRate; }
    public void setTaxRate(Double taxRate) { this.taxRate = taxRate; }

    public Double getTaxAmount() { return taxAmount; }
    public void setTaxAmount(Double taxAmount) { this.taxAmount = taxAmount; }

    public Double getLineTotal() { return lineTotal; }
    public void setLineTotal(Double lineTotal) { this.lineTotal = lineTotal; }
}
