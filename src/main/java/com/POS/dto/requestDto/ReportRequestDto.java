package com.POS.dto.requestDto;
import java.time.LocalDate;

public class ReportRequestDto {

    private String reportId;
    private String tenantId;
    private String branchId;
    private String title;
    private String type;
    private String description;
    private String generatedById;
    private String generatedByName;
    private LocalDate periodStart;
    private LocalDate periodEnd;

    public ReportRequestDto() {
    }

    public ReportRequestDto(String reportId, String tenantId, String branchId, String title, String type,
                            String description, String generatedById, String generatedByName,
                            LocalDate periodStart, LocalDate periodEnd) {
        this.reportId = reportId;
        this.tenantId = tenantId;
        this.branchId = branchId;
        this.title = title;
        this.type = type;
        this.description = description;
        this.generatedById = generatedById;
        this.generatedByName = generatedByName;
        this.periodStart = periodStart;
        this.periodEnd = periodEnd;
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

    public LocalDate getPeriodStart() {
        return periodStart;
    }

    public void setPeriodStart(LocalDate periodStart) {
        this.periodStart = periodStart;
    }

    public LocalDate getPeriodEnd() {
        return periodEnd;
    }

    public void setPeriodEnd(LocalDate periodEnd) {
        this.periodEnd = periodEnd;
    }
}
