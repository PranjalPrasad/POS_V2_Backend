package com.POS.dto.requestDto;

public class CustomerRequestDto {

    private String customerId;
    private String tenantId;
    private String branchId;
    private String name;
    private String email;
    private String phone;
    private String address;
    private String customerGroup;
    private String status;
    private Boolean isActive;

    public CustomerRequestDto() {
    }

    public CustomerRequestDto(String customerId, String tenantId, String branchId, String name,
                              String email, String phone, String address, String customerGroup,
                              String status, Boolean isActive) {
        this.customerId = customerId;
        this.tenantId = tenantId;
        this.branchId = branchId;
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.address = address;
        this.customerGroup = customerGroup;
        this.status = status;
        this.isActive = isActive;
    }

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCustomerGroup() {
        return customerGroup;
    }

    public void setCustomerGroup(String customerGroup) {
        this.customerGroup = customerGroup;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Boolean getIsActive() {
        return isActive;
    }

    public void setIsActive(Boolean isActive) {
        this.isActive = isActive;
    }
}
