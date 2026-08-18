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

    TableResponseDto updateTable(Long id, TableRequestDto requestDto);

    TableResponseDto patchTable(Long id, TableRequestDto requestDto);

    TableResponseDto assignOrder(Long id, String orderId);

    TableResponseDto clearOrder(Long id);

    void deleteTable(Long id);
}
