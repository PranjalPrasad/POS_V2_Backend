package com.POS.dto.requestDto;

public class StaffRequestDto {

    private String staffId;
    private String tenantId;
    private String branchId;
    private String name;
    private String role;
    private String email;
    private String phone;
    private String mobileNumber;
    private String password;
    private String schedule;
    private String status;
    private Boolean isActive;

    public StaffRequestDto() {
    }

    public StaffRequestDto(String staffId, String tenantId, String branchId, String name, String role,
                           String email, String phone, String mobileNumber, String password,
                           String schedule, String status, Boolean isActive) {
        this.staffId = staffId;
        this.tenantId = tenantId;
        this.branchId = branchId;
        this.name = name;
        this.role = role;
        this.email = email;
        this.phone = phone;
        this.mobileNumber = mobileNumber;
        this.password = password;
        this.schedule = schedule;
        this.status = status;
        this.isActive = isActive;
    }

    public String getStaffId() {
        return staffId;
    }

    public void setStaffId(String staffId) {
        this.staffId = staffId;
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

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
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

    public String getMobileNumber() {
        return mobileNumber;
    }

    public void setMobileNumber(String mobileNumber) {
        this.mobileNumber = mobileNumber;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getSchedule() {
        return schedule;
    }

    public void setSchedule(String schedule) {
        this.schedule = schedule;
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
