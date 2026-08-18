package com.POS.service;


import com.POS.dto.requestDto.TableRequestDto;
import com.POS.dto.resposneDto.TableResponseDto;

import java.util.List;

public interface TableService {

    TableResponseDto createTable(TableRequestDto requestDto);

    List<TableResponseDto> getAllTables();

    TableResponseDto getTableById(Long id);

    TableResponseDto getTableByTableId(String tableId);

    List<TableResponseDto> getTablesByTenantAndBranch(String tenantId, String branchId);

    List<TableResponseDto> getTablesByStatus(String currentStatus);

    List<TableResponseDto> getTablesBySection(String sectionId);

    List<TableResponseDto> getActiveTables();

    // Full update - all fields expected
    TableResponseDto updateTable(Long id, TableRequestDto requestDto);

    // Partial update - only non-null fields applied
    TableResponseDto patchTable(Long id, TableRequestDto requestDto);

    // Convenience: assign an order and flip status to OCCUPIED
    TableResponseDto assignOrder(Long id, String orderId);

    // Convenience: clear the order and flip status back to AVAILABLE
    TableResponseDto clearOrder(Long id);

    void deleteTable(Long id);
}
