package com.POS.controller;

import com.POS.dto.requestDto.ReportRequestDto;
import com.POS.dto.responseDto.ReportResponseDto;
import com.POS.exception.ReportNotFoundException;
import com.POS.service.ReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/reports")
public class ReportController {

    private final ReportService reportService;

    @Autowired
    public ReportController(ReportService reportService) {
        this.reportService = reportService;
    }

    @PostMapping("/generate-report")
    public ResponseEntity<?> generateReport(@RequestBody ReportRequestDto requestDto) {
        try {
            ReportResponseDto response = reportService.generateReport(requestDto);
            return new ResponseEntity<>(response, HttpStatus.CREATED);
        } catch (RuntimeException ex) {
            return new ResponseEntity<>(ex.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/get-all-reports")
    public ResponseEntity<List<ReportResponseDto>> getAllReports() {
        return new ResponseEntity<>(reportService.getAllReports(), HttpStatus.OK);
    }

    @GetMapping("/get-report-by-id/{reportId}")
    public ResponseEntity<?> getReportById(@PathVariable String reportId) {
        try {
            return new ResponseEntity<>(reportService.getReportById(reportId), HttpStatus.OK);
        } catch (ReportNotFoundException ex) {
            return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/get-reports-by-type/type/{type}")
    public ResponseEntity<List<ReportResponseDto>> getReportsByType(@PathVariable String type) {
        return new ResponseEntity<>(reportService.getReportsByType(type), HttpStatus.OK);
    }

    @PatchMapping("/increment-download/{reportId}/download")
    public ResponseEntity<?> incrementDownload(@PathVariable String reportId) {
        try {
            return new ResponseEntity<>(reportService.incrementDownloadCount(reportId), HttpStatus.OK);
        } catch (ReportNotFoundException ex) {
            return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/delete-report/{reportId}")
    public ResponseEntity<?> deleteReport(@PathVariable String reportId) {
        try {
            reportService.deleteReport(reportId);
            return new ResponseEntity<>("Report deleted successfully", HttpStatus.OK);
        } catch (ReportNotFoundException ex) {
            return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND);
        }
    }
}
