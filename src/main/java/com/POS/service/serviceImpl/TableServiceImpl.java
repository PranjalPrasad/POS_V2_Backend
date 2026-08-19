package com.POS.service.serviceImpl;

import com.POS.dto.requestDto.TableRequestDto;
import com.POS.dto.responseDto.TableResponseDto;
import com.POS.entity.TableEntity;
import com.POS.repository.TableRepository;
import com.POS.service.TableService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

@Service
public class TableServiceImpl implements TableService {

    private final TableRepository tableRepository;

    @Autowired
    public TableServiceImpl(TableRepository tableRepository) {
        this.tableRepository = tableRepository;
    }

    @Override
    public TableResponseDto createTable(TableRequestDto requestDto) {
        TableEntity entity = new TableEntity();
        mapDtoToEntity(requestDto, entity);
        entity.setStatusLastUpdated(LocalDateTime.now());
        entity.setCreatedAt(LocalDateTime.now());
        entity.setUpdatedAt(LocalDateTime.now());
        TableEntity saved = tableRepository.save(entity);
        return mapEntityToDto(saved);
    }

    @Override
    public List<TableResponseDto> getAllTables() {
        return tableRepository.findAll()
                .stream()
                .map(this::mapEntityToDto)
                .collect(Collectors.toList());
    }

    @Override
    public TableResponseDto getTableById(Long id) {
        TableEntity entity = tableRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Table not found with id: " + id));
        return mapEntityToDto(entity);
    }

    @Override
    public TableResponseDto getTableByTableId(String tableId) {
        TableEntity entity = tableRepository.findByTableId(tableId)
                .orElseThrow(() -> new NoSuchElementException("Table not found with tableId: " + tableId));
        return mapEntityToDto(entity);
    }

    @Override
    public List<TableResponseDto> getTablesByTenantAndBranch(String tenantId, String branchId) {
        return tableRepository.findByTenantIdAndBranchId(tenantId, branchId)
                .stream()
                .map(this::mapEntityToDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<TableResponseDto> getTablesByStatus(String currentStatus) {
        return tableRepository.findByCurrentStatus(currentStatus)
                .stream()
                .map(this::mapEntityToDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<TableResponseDto> getTablesBySection(String sectionId) {
        return tableRepository.findBySectionId(sectionId)
                .stream()
                .map(this::mapEntityToDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<TableResponseDto> getActiveTables() {
        return tableRepository.findByIsActive(true)
                .stream()
                .map(this::mapEntityToDto)
                .collect(Collectors.toList());
    }

    @Override
    public TableResponseDto updateTable(Long id, TableRequestDto requestDto) {
        TableEntity entity = tableRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Table not found with id: " + id));
        mapDtoToEntity(requestDto, entity);
        entity.setStatusLastUpdated(LocalDateTime.now());
        entity.setUpdatedAt(LocalDateTime.now());
        TableEntity saved = tableRepository.save(entity);
        return mapEntityToDto(saved);
    }

    @Override
    public TableResponseDto patchTable(Long id, TableRequestDto requestDto) {
        TableEntity entity = tableRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Table not found with id: " + id));

        if (requestDto.getTableId() != null) entity.setTableId(requestDto.getTableId());
        if (requestDto.getTenantId() != null) entity.setTenantId(requestDto.getTenantId());
        if (requestDto.getBranchId() != null) entity.setBranchId(requestDto.getBranchId());

        if (requestDto.getTableNumber() != null) entity.setTableNumber(requestDto.getTableNumber());
        if (requestDto.getName() != null) entity.setName(requestDto.getName());
        if (requestDto.getCapacity() != null) entity.setCapacity(requestDto.getCapacity());
        if (requestDto.getSectionId() != null) entity.setSectionId(requestDto.getSectionId());
        if (requestDto.getSectionName() != null) entity.setSectionName(requestDto.getSectionName());

        boolean statusChanged = false;
        if (requestDto.getCurrentStatus() != null) {
            entity.setCurrentStatus(requestDto.getCurrentStatus());
            statusChanged = true;
        }

        if (requestDto.getCurrentOrderId() != null) {
            entity.setCurrentOrderId(requestDto.getCurrentOrderId());
        }

        if (requestDto.getIsActive() != null) entity.setIsActive(requestDto.getIsActive());

        if (statusChanged) {
            entity.setStatusLastUpdated(LocalDateTime.now());
        }

        entity.setUpdatedAt(LocalDateTime.now());

        TableEntity saved = tableRepository.save(entity);
        return mapEntityToDto(saved);
    }

    @Override
    public TableResponseDto assignOrder(Long id, String orderId) {
        TableEntity entity = tableRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Table not found with id: " + id));
        entity.setCurrentOrderId(orderId);
        entity.setCurrentStatus("OCCUPIED");
        entity.setStatusLastUpdated(LocalDateTime.now());
        entity.setUpdatedAt(LocalDateTime.now());
        TableEntity saved = tableRepository.save(entity);
        return mapEntityToDto(saved);
    }

    @Override
    public TableResponseDto clearOrder(Long id) {
        TableEntity entity = tableRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Table not found with id: " + id));
        entity.setCurrentOrderId(null);
        entity.setCurrentStatus("AVAILABLE");
        entity.setStatusLastUpdated(LocalDateTime.now());
        entity.setUpdatedAt(LocalDateTime.now());
        TableEntity saved = tableRepository.save(entity);
        return mapEntityToDto(saved);
    }

    @Override
    public void deleteTable(Long id) {
        if (!tableRepository.existsById(id)) {
            throw new NoSuchElementException("Table not found with id: " + id);
        }
        tableRepository.deleteById(id);
    }

    // ---------------- helper / mapping methods ----------------

    private void mapDtoToEntity(TableRequestDto dto, TableEntity entity) {
        entity.setTableId(dto.getTableId());
        entity.setTenantId(dto.getTenantId());
        entity.setBranchId(dto.getBranchId());

        entity.setTableNumber(dto.getTableNumber());
        entity.setName(dto.getName());
        entity.setCapacity(dto.getCapacity());
        entity.setSectionId(dto.getSectionId());
        entity.setSectionName(dto.getSectionName());

        entity.setCurrentStatus(dto.getCurrentStatus());
        entity.setCurrentOrderId(dto.getCurrentOrderId());

        entity.setIsActive(dto.getIsActive());
    }

    private TableResponseDto mapEntityToDto(TableEntity entity) {
        TableResponseDto dto = new TableResponseDto();
        dto.setId(entity.getId());
        dto.setTableId(entity.getTableId());
        dto.setTenantId(entity.getTenantId());
        dto.setBranchId(entity.getBranchId());

        dto.setTableNumber(entity.getTableNumber());
        dto.setName(entity.getName());
        dto.setCapacity(entity.getCapacity());
        dto.setSectionId(entity.getSectionId());
        dto.setSectionName(entity.getSectionName());

        dto.setCurrentStatus(entity.getCurrentStatus());
        dto.setStatusLastUpdated(entity.getStatusLastUpdated());

        dto.setCurrentOrderId(entity.getCurrentOrderId());

        dto.setIsActive(entity.getIsActive());
        dto.setCreatedAt(entity.getCreatedAt());
        dto.setUpdatedAt(entity.getUpdatedAt());
        return dto;
    }
}
