package com.POS.dto.responseDto;

import java.time.LocalDateTime;

public class ReportResponseDto {

    private String reportId;
    private String tenantId;
    private String branchId;
    private String title;
    private String type;
    private String description;
    private String generatedById;
    private String generatedByName;
    private LocalDateTime generatedOn;
    private String fileSize;
    private String status;
    private Integer downloadCount;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public ReportResponseDto() {
    }

    public ReportResponseDto(String reportId, String tenantId, String branchId, String title, String type,
                             String description, String generatedById, String generatedByName,
                             LocalDateTime generatedOn, String fileSize, String status, Integer downloadCount,
                             LocalDateTime createdAt, LocalDateTime updatedAt) {
        this.reportId = reportId;
        this.tenantId = tenantId;
        this.branchId = branchId;
        this.title = title;
        this.type = type;
        this.description = description;
        this.generatedById = generatedById;
        this.generatedByName = generatedByName;
        this.generatedOn = generatedOn;
        this.fileSize = fileSize;
        this.status = status;
        this.downloadCount = downloadCount;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public String getReportId() {
        return reportId;
    }

    public void setReportId(String reportId) {
        this.reportId = reportId;
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

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getGeneratedById() {
        return generatedById;
    }

    public void setGeneratedById(String generatedById) {
        this.generatedById = generatedById;
    }

    public String getGeneratedByName() {
        return generatedByName;
    }

    public void setGeneratedByName(String generatedByName) {
        this.generatedByName = generatedByName;
    }

    public LocalDateTime getGeneratedOn() {
        return generatedOn;
    }

    public void setGeneratedOn(LocalDateTime generatedOn) {
        this.generatedOn = generatedOn;
    }

    public String getFileSize() {
        return fileSize;
    }

    public void setFileSize(String fileSize) {
        this.fileSize = fileSize;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Integer getDownloadCount() {
        return downloadCount;
    }

    public void setDownloadCount(Integer downloadCount) {
        this.downloadCount = downloadCount;
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
