package com.POS.service.serviceImpl;
import com.POS.dto.requestDto.ReportRequestDto;
import com.POS.dto.responseDto.ReportResponseDto;
import com.POS.entity.ReportEntity;
import com.POS.exception.ReportNotFoundException;
import com.POS.repository.ReportRepository;
import com.POS.service.ReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ReportServiceImpl implements ReportService {

    private final ReportRepository reportRepository;

    @Autowired
    public ReportServiceImpl(ReportRepository reportRepository) {
        this.reportRepository = reportRepository;
    }

    @Override
    public ReportResponseDto generateReport(ReportRequestDto requestDto) {
        if (requestDto.getReportId() != null && reportRepository.existsByReportId(requestDto.getReportId())) {
            throw new RuntimeException("Report already exists with reportId: " + requestDto.getReportId());
        }

        ReportEntity entity = new ReportEntity();
        entity.setReportId(requestDto.getReportId());
        entity.setTenantId(requestDto.getTenantId());
        entity.setBranchId(requestDto.getBranchId());
        entity.setTitle(requestDto.getTitle());
        entity.setType(requestDto.getType());
        entity.setDescription(requestDto.getDescription());
        entity.setGeneratedById(requestDto.getGeneratedById());
        entity.setGeneratedByName(requestDto.getGeneratedByName());
        entity.setPeriodStart(requestDto.getPeriodStart());
        entity.setPeriodEnd(requestDto.getPeriodEnd());
        // status, generatedOn, fileSize, downloadCount get sensible defaults
        // in ReportEntity's @PrePersist (onCreate)

        // NOTE: This is where actual report generation would happen —
        // querying SaleEntity/ProductEntity/InventoryEntity/CustomerEntity
        // filtered by periodStart/periodEnd based on requestDto.getType(),
        // building a file, and setting the real fileSize. Kept as metadata
        // only for now, as per the module note.

        ReportEntity saved = reportRepository.save(entity);
        return mapToResponseDto(saved);
    }

    @Override
    public List<ReportResponseDto> getAllReports() {
        return reportRepository.findAll()
                .stream()
                .map(this::mapToResponseDto)
                .collect(Collectors.toList());
    }

    @Override
    public ReportResponseDto getReportById(String reportId) {
        ReportEntity entity = reportRepository.findByReportId(reportId)
                .orElseThrow(() -> new ReportNotFoundException("Report not found with reportId: " + reportId));
        return mapToResponseDto(entity);
    }

    @Override
    public List<ReportResponseDto> getReportsByType(String type) {
        return reportRepository.findByType(type)
                .stream()
                .map(this::mapToResponseDto)
                .collect(Collectors.toList());
    }

    @Override
    public ReportResponseDto incrementDownloadCount(String reportId) {
        ReportEntity entity = reportRepository.findByReportId(reportId)
                .orElseThrow(() -> new ReportNotFoundException("Report not found with reportId: " + reportId));

        int current = entity.getDownloadCount() != null ? entity.getDownloadCount() : 0;
        entity.setDownloadCount(current + 1);

        ReportEntity updated = reportRepository.save(entity);
        return mapToResponseDto(updated);
    }

    @Override
    public void deleteReport(String reportId) {
        ReportEntity entity = reportRepository.findByReportId(reportId)
                .orElseThrow(() -> new ReportNotFoundException("Report not found with reportId: " + reportId));
        reportRepository.delete(entity);
    }

    private ReportResponseDto mapToResponseDto(ReportEntity entity) {
        return new ReportResponseDto(
                entity.getReportId(),
                entity.getTenantId(),
                entity.getBranchId(),
                entity.getTitle(),
                entity.getType(),
                entity.getDescription(),
                entity.getGeneratedById(),
                entity.getGeneratedByName(),
                entity.getGeneratedOn(),
                entity.getFileSize(),
                entity.getStatus(),
                entity.getDownloadCount(),
                entity.getCreatedAt(),
                entity.getUpdatedAt()
        );
    }
}
