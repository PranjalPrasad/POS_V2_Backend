package com.POS.service;


import com.POS.dto.requestDto.StaffRequestDto;
import com.POS.dto.responseDto.StaffResponseDto;

import java.util.List;

public interface StaffService {

    StaffResponseDto createStaff(StaffRequestDto requestDto);

    List<StaffResponseDto> getAllStaff();

    StaffResponseDto getStaffById(String staffId);

    List<StaffResponseDto> getStaffByRole(String role);

    List<StaffResponseDto> getStaffByStatus(String status);

    StaffResponseDto updateStaff(String staffId, StaffRequestDto requestDto);

    StaffResponseDto patchStaff(String staffId, StaffRequestDto requestDto);

    void deleteStaff(String staffId);
}
