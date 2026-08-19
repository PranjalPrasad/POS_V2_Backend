package com.POS.service.serviceImpl;

import com.POS.dto.requestDto.StaffRequestDto;
import com.POS.dto.responseDto.StaffResponseDto;
import com.POS.entity.StaffEntity;
import com.POS.exception.StaffNotFoundException;
import com.POS.repository.StaffRepository;
import com.POS.service.StaffService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class StaffServiceImpl implements StaffService {

    private final StaffRepository staffRepository;

    @Autowired
    public StaffServiceImpl(StaffRepository staffRepository) {
        this.staffRepository = staffRepository;
    }

    @Override
    public StaffResponseDto createStaff(StaffRequestDto requestDto) {
        if (requestDto.getStaffId() != null && staffRepository.existsByStaffId(requestDto.getStaffId())) {
            throw new RuntimeException("Staff already exists with staffId: " + requestDto.getStaffId());
        }

        StaffEntity entity = new StaffEntity();
        entity.setStaffId(requestDto.getStaffId());
        entity.setTenantId(requestDto.getTenantId());
        entity.setBranchId(requestDto.getBranchId());
        entity.setName(requestDto.getName());
        entity.setRole(requestDto.getRole());
        entity.setEmail(requestDto.getEmail());
        entity.setPhone(requestDto.getPhone());
        entity.setMobileNumber(requestDto.getMobileNumber());
        entity.setPassword(requestDto.getPassword());
        entity.setSchedule(requestDto.getSchedule());
        entity.setStatus(requestDto.getStatus());
        entity.setIsActive(requestDto.getIsActive());

        StaffEntity saved = staffRepository.save(entity);
        return mapToResponseDto(saved);
    }

    @Override
    public List<StaffResponseDto> getAllStaff() {
        return staffRepository.findAll()
                .stream()
                .map(this::mapToResponseDto)
                .collect(Collectors.toList());
    }

    @Override
    public StaffResponseDto getStaffById(String staffId) {
        StaffEntity entity = staffRepository.findByStaffId(staffId)
                .orElseThrow(() -> new StaffNotFoundException("Staff not found with staffId: " + staffId));
        return mapToResponseDto(entity);
    }

    @Override
    public List<StaffResponseDto> getStaffByRole(String role) {
        return staffRepository.findByRole(role)
                .stream()
                .map(this::mapToResponseDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<StaffResponseDto> getStaffByStatus(String status) {
        return staffRepository.findByStatus(status)
                .stream()
                .map(this::mapToResponseDto)
                .collect(Collectors.toList());
    }

    @Override
    public StaffResponseDto updateStaff(String staffId, StaffRequestDto requestDto) {
        StaffEntity entity = staffRepository.findByStaffId(staffId)
                .orElseThrow(() -> new StaffNotFoundException("Staff not found with staffId: " + staffId));

        // Full update — every field is overwritten
        entity.setTenantId(requestDto.getTenantId());
        entity.setBranchId(requestDto.getBranchId());
        entity.setName(requestDto.getName());
        entity.setRole(requestDto.getRole());
        entity.setEmail(requestDto.getEmail());
        entity.setPhone(requestDto.getPhone());
        entity.setMobileNumber(requestDto.getMobileNumber());
        entity.setPassword(requestDto.getPassword());
        entity.setSchedule(requestDto.getSchedule());
        entity.setStatus(requestDto.getStatus());
        entity.setIsActive(requestDto.getIsActive());

        StaffEntity updated = staffRepository.save(entity);
        return mapToResponseDto(updated);
    }

    @Override
    public StaffResponseDto patchStaff(String staffId, StaffRequestDto requestDto) {
        StaffEntity entity = staffRepository.findByStaffId(staffId)
                .orElseThrow(() -> new StaffNotFoundException("Staff not found with staffId: " + staffId));

        // Partial update — only overwrite fields that were actually sent
        if (requestDto.getTenantId() != null) {
            entity.setTenantId(requestDto.getTenantId());
        }
        if (requestDto.getBranchId() != null) {
            entity.setBranchId(requestDto.getBranchId());
        }
        if (requestDto.getName() != null) {
            entity.setName(requestDto.getName());
        }
        if (requestDto.getRole() != null) {
            entity.setRole(requestDto.getRole());
        }
        if (requestDto.getEmail() != null) {
            entity.setEmail(requestDto.getEmail());
        }
        if (requestDto.getPhone() != null) {
            entity.setPhone(requestDto.getPhone());
        }
        if (requestDto.getMobileNumber() != null) {
            entity.setMobileNumber(requestDto.getMobileNumber());
        }
        if (requestDto.getPassword() != null) {
            entity.setPassword(requestDto.getPassword());
        }
        if (requestDto.getSchedule() != null) {
            entity.setSchedule(requestDto.getSchedule());
        }
        if (requestDto.getStatus() != null) {
            entity.setStatus(requestDto.getStatus());
        }
        if (requestDto.getIsActive() != null) {
            entity.setIsActive(requestDto.getIsActive());
        }

        StaffEntity patched = staffRepository.save(entity);
        return mapToResponseDto(patched);
    }

    @Override
    public void deleteStaff(String staffId) {
        StaffEntity entity = staffRepository.findByStaffId(staffId)
                .orElseThrow(() -> new StaffNotFoundException("Staff not found with staffId: " + staffId));
        staffRepository.delete(entity);
    }

    private StaffResponseDto mapToResponseDto(StaffEntity entity) {
        return new StaffResponseDto(
                entity.getStaffId(),
                entity.getTenantId(),
                entity.getBranchId(),
                entity.getName(),
                entity.getRole(),
                entity.getEmail(),
                entity.getPhone(),
                entity.getSchedule(),
                entity.getStatus(),
                entity.getIsActive(),
                entity.getCreatedAt(),
                entity.getUpdatedAt()
        );
    }
}
