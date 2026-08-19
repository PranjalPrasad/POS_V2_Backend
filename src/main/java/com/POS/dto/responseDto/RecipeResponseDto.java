package com.POS.dto.responseDto;
import java.time.LocalDateTime;
import java.util.List;

public class RecipeResponseDto {

    private Long id;
    private String recipeId;
    private String tenantId;
    private String branchId;

    private String productId;
    private String productName;
    private String productSku;

    private String type;

    private Double yieldQuantity;
    private String yieldUnit;

    private Double totalMaterialCost;
    private Double sellingPrice;
    private Double estimatedProfit;

    private Boolean autoDeductStock;
    private Boolean isActive;

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    private List<IngredientResponseDto> ingredients;

    public RecipeResponseDto() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getRecipeId() {
        return recipeId;
    }

    public void setRecipeId(String recipeId) {
        this.recipeId = recipeId;
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

    public String getProductSku() {
        return productSku;
    }

    public void setProductSku(String productSku) {
        this.productSku = productSku;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Double getYieldQuantity() {
        return yieldQuantity;
    }

    public void setYieldQuantity(Double yieldQuantity) {
        this.yieldQuantity = yieldQuantity;
    }

    public String getYieldUnit() {
        return yieldUnit;
    }

    public void setYieldUnit(String yieldUnit) {
        this.yieldUnit = yieldUnit;
    }

    public Double getTotalMaterialCost() {
        return totalMaterialCost;
    }

    public void setTotalMaterialCost(Double totalMaterialCost) {
        this.totalMaterialCost = totalMaterialCost;
    }

    public Double getSellingPrice() {
        return sellingPrice;
    }

    public void setSellingPrice(Double sellingPrice) {
        this.sellingPrice = sellingPrice;
    }

    public Double getEstimatedProfit() {
        return estimatedProfit;
    }

    public void setEstimatedProfit(Double estimatedProfit) {
        this.estimatedProfit = estimatedProfit;
    }

    public Boolean getAutoDeductStock() {
        return autoDeductStock;
    }

    public void setAutoDeductStock(Boolean autoDeductStock) {
        this.autoDeductStock = autoDeductStock;
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

    public List<IngredientResponseDto> getIngredients() {
        return ingredients;
    }

    public void setIngredients(List<IngredientResponseDto> ingredients) {
        this.ingredients = ingredients;
    }
}
