package com.POS.service;


import com.POS.dto.requestDto.InventoryRequestDto;
import com.POS.dto.responseDto.InventoryResponseDto;

import java.util.List;

public interface InventoryService {

    InventoryResponseDto createInventory(InventoryRequestDto requestDto);

    List<InventoryResponseDto> getAllInventory();

    InventoryResponseDto getInventoryById(Long id);

    InventoryResponseDto getInventoryByInventoryId(String inventoryId);

    List<InventoryResponseDto> getInventoryByTenantAndBranch(String tenantId, String branchId);

    List<InventoryResponseDto> getInventoryByStockStatus(String stockStatus);

    List<InventoryResponseDto> getLowStockInventory();

    // Full update - all fields expected
    InventoryResponseDto updateInventory(Long id, InventoryRequestDto requestDto);

    // Partial update - only non-null fields in requestDto are applied
    InventoryResponseDto patchInventory(Long id, InventoryRequestDto requestDto);

    void deleteInventory(Long id);
}
