package com.POS.entity;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import java.time.LocalDateTime;

@Entity
@Table(name = "products")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "product_id", unique = true, nullable = false)
    private String productId;

    @Column(name = "tenant_id", nullable = false)
    private String tenantId;

    @Column(name = "branch_id", nullable = false)
    private String branchId;

    // ---------- basicInformation (flattened) ----------
    @Column(name = "name")
    private String name;

    @Column(name = "code")
    private String code;

    @Column(name = "sku")
    private String sku;

    @Column(name = "barcode")
    private String barcode;

    @Column(name = "description")
    private String description;

    @Column(name = "product_type")
    private String productType;

    // ---------- classification (flattened) ----------
    @Column(name = "category_id")
    private String categoryId;

    @Column(name = "category_name")
    private String categoryName;

    @Column(name = "brand_id")
    private String brandId;

    @Column(name = "brand_name")
    private String brandName;

    // ---------- pricing (flattened) ----------
    @Column(name = "cost_price")
    private Double costPrice;

    @Column(name = "selling_price")
    private Double sellingPrice;

    @Column(name = "mrp")
    private Double mrp;

    @Column(name = "currency")
    private String currency;

    // ---------- unit (flattened) ----------
    @Column(name = "unit_id")
    private String unitId;

    @Column(name = "unit_name")
    private String unitName;

    @Column(name = "unit_code")
    private String unitCode;

    // ---------- tax (flattened) ----------
    @Column(name = "tax_id")
    private String taxId;

    @Column(name = "tax_name")
    private String taxName;

    @Column(name = "tax_rate")
    private Double taxRate;

    // ---------- inventorySettings (flattened) ----------
    @Column(name = "track_inventory")
    private Boolean trackInventory;

    @Column(name = "track_batch")
    private Boolean trackBatch;

    @Column(name = "track_expiry")
    private Boolean trackExpiry;

    @Column(name = "minimum_stock")
    private Integer minimumStock;

    @Column(name = "maximum_stock")
    private Integer maximumStock;

    // ---------- variants ----------
    // Simplified: stored as comma separated variant names/codes.
    // (Kept as a plain column instead of a separate embedded/child entity as requested.)
    @Column(name = "variants")
    private String variants;

    @Column(name = "is_active")
    private Boolean isActive;

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    public Product() {
    }

    // ---------------- Getters & Setters ----------------

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public String getTenantId() {
        return tenantId;
    }

    public void setTenantId(String tenantId) {
        this.tenantId = tenantId;
    }

    public String getBranchId() {
        return branchId;
    }

    public void setBranchId(String branchId) {
        this.branchId = branchId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getSku() {
        return sku;
    }

    public void setSku(String sku) {
        this.sku = sku;
    }

    public String getBarcode() {
        return barcode;
    }

    public void setBarcode(String barcode) {
        this.barcode = barcode;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getProductType() {
        return productType;
    }

    public void setProductType(String productType) {
        this.productType = productType;
    }

    public String getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(String categoryId) {
        this.categoryId = categoryId;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public String getBrandId() {
        return brandId;
    }

    public void setBrandId(String brandId) {
        this.brandId = brandId;
    }

    public String getBrandName() {
        return brandName;
    }

    public void setBrandName(String brandName) {
        this.brandName = brandName;
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

    public Double getMrp() {
        return mrp;
    }

    public void setMrp(Double mrp) {
        this.mrp = mrp;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public String getUnitId() {
        return unitId;
    }

    public void setUnitId(String unitId) {
        this.unitId = unitId;
    }

    public String getUnitName() {
        return unitName;
    }

    public void setUnitName(String unitName) {
        this.unitName = unitName;
    }

    public String getUnitCode() {
        return unitCode;
    }

    public void setUnitCode(String unitCode) {
        this.unitCode = unitCode;
    }

    public String getTaxId() {
        return taxId;
    }

    public void setTaxId(String taxId) {
        this.taxId = taxId;
    }

    public String getTaxName() {
        return taxName;
    }

    public void setTaxName(String taxName) {
        this.taxName = taxName;
    }

    public Double getTaxRate() {
        return taxRate;
    }

    public void setTaxRate(Double taxRate) {
        this.taxRate = taxRate;
    }

    public Boolean getTrackInventory() {
        return trackInventory;
    }

    public void setTrackInventory(Boolean trackInventory) {
        this.trackInventory = trackInventory;
    }

    public Boolean getTrackBatch() {
        return trackBatch;
    }

    public void setTrackBatch(Boolean trackBatch) {
        this.trackBatch = trackBatch;
    }

    public Boolean getTrackExpiry() {
        return trackExpiry;
    }

    public void setTrackExpiry(Boolean trackExpiry) {
        this.trackExpiry = trackExpiry;
    }

    public Integer getMinimumStock() {
        return minimumStock;
    }

    public void setMinimumStock(Integer minimumStock) {
        this.minimumStock = minimumStock;
    }

    public Integer getMaximumStock() {
        return maximumStock;
    }

    public void setMaximumStock(Integer maximumStock) {
        this.maximumStock = maximumStock;
    }

    public String getVariants() {
        return variants;
    }

    public void setVariants(String variants) {
        this.variants = variants;
    }

    public Boolean getIsActive() {
        return isActive;
    }

    public void setIsActive(Boolean isActive) {
        this.isActive = isActive;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }
}
