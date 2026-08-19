package com.POS.service;

import com.POS.dto.requestDto.ReportRequestDto;
import com.POS.dto.responseDto.ReportResponseDto;

import java.util.List;

public interface ReportService {

    ReportResponseDto generateReport(ReportRequestDto requestDto);

    List<ReportResponseDto> getAllReports();

    ReportResponseDto getReportById(String reportId);

    List<ReportResponseDto> getReportsByType(String type);

    ReportResponseDto incrementDownloadCount(String reportId);

    void deleteReport(String reportId);
}
