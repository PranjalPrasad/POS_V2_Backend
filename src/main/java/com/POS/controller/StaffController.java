package com.POS.controller;

import com.POS.dto.requestDto.StaffRequestDto;
import com.POS.dto.responseDto.StaffResponseDto;
import com.POS.exception.StaffNotFoundException;
import com.POS.service.StaffService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/staff")
public class StaffController {

    private final StaffService staffService;

    @Autowired
    public StaffController(StaffService staffService) {
        this.staffService = staffService;
    }

    @PostMapping("/create-staff")
    public ResponseEntity<?> createStaff(@RequestBody StaffRequestDto requestDto) {
        try {
            StaffResponseDto response = staffService.createStaff(requestDto);
            return new ResponseEntity<>(response, HttpStatus.CREATED);
        } catch (RuntimeException ex) {
            return new ResponseEntity<>(ex.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/get-all-staff")
    public ResponseEntity<List<StaffResponseDto>> getAllStaff() {
        return new ResponseEntity<>(staffService.getAllStaff(), HttpStatus.OK);
    }

    @GetMapping("/get-staff-by-id/{staffId}")
    public ResponseEntity<?> getStaffById(@PathVariable String staffId) {
        try {
            return new ResponseEntity<>(staffService.getStaffById(staffId), HttpStatus.OK);
        } catch (StaffNotFoundException ex) {
            return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/get-staff-by-role/role/{role}")
    public ResponseEntity<List<StaffResponseDto>> getStaffByRole(@PathVariable String role) {
        return new ResponseEntity<>(staffService.getStaffByRole(role), HttpStatus.OK);
    }

    @GetMapping("/get-staff-by-status/status/{status}")
    public ResponseEntity<List<StaffResponseDto>> getStaffByStatus(@PathVariable String status) {
        return new ResponseEntity<>(staffService.getStaffByStatus(status), HttpStatus.OK);
    }

    @PutMapping("/update-staff/{staffId}")
    public ResponseEntity<?> updateStaff(@PathVariable String staffId,
                                         @RequestBody StaffRequestDto requestDto) {
        try {
            return new ResponseEntity<>(staffService.updateStaff(staffId, requestDto), HttpStatus.OK);
        } catch (StaffNotFoundException ex) {
            return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    @PatchMapping("/patch-staff/{staffId}")
    public ResponseEntity<?> patchStaff(@PathVariable String staffId,
                                        @RequestBody StaffRequestDto requestDto) {
        try {
            return new ResponseEntity<>(staffService.patchStaff(staffId, requestDto), HttpStatus.OK);
        } catch (StaffNotFoundException ex) {
            return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/delete-staff/{staffId}")
    public ResponseEntity<?> deleteStaff(@PathVariable String staffId) {
        try {
            staffService.deleteStaff(staffId);
            return new ResponseEntity<>("Staff deleted successfully", HttpStatus.OK);
        } catch (StaffNotFoundException ex) {
            return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND);
        }
    }
}
