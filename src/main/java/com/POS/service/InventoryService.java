package com.POS.service;

import com.POS.dto.requestDto.InventoryRequestDto;
import com.POS.dto.responseDto.InventoryResponseDto;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface InventoryService {

    InventoryResponseDto createInventory(InventoryRequestDto requestDto, MultipartFile productImage) throws IOException;

    List<InventoryResponseDto> getAllInventory();

    InventoryResponseDto getInventoryById(Long id);

    InventoryResponseDto getInventoryByInventoryId(String inventoryId);

    List<InventoryResponseDto> getInventoryByTenantAndBranch(String tenantId, String branchId);

    List<InventoryResponseDto> getInventoryByStockStatus(String stockStatus);

    List<InventoryResponseDto> getLowStockInventory();

    // Full update - all fields expected
    InventoryResponseDto updateInventory(Long id, InventoryRequestDto requestDto, MultipartFile productImage) throws IOException;

    // Partial update - only non-null fields in requestDto are applied
    InventoryResponseDto patchInventory(Long id, InventoryRequestDto requestDto, MultipartFile productImage) throws IOException;

    void deleteInventory(Long id);
}