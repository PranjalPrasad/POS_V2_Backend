package com.POS.controller;

import com.POS.dto.requestDto.InventoryRequestDto;
import com.POS.dto.responseDto.InventoryResponseDto;
import com.POS.service.InventoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/api/v1/inventory")
public class InventoryController {

    private final InventoryService inventoryService;

    @Autowired
    public InventoryController(InventoryService inventoryService) {
        this.inventoryService = inventoryService;
    }

    @PostMapping(value = "/create-inventory", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<InventoryResponseDto> createInventory(
            @ModelAttribute InventoryRequestDto requestDto,
            @RequestParam(value = "productImage", required = false) MultipartFile productImage) throws IOException {
        InventoryResponseDto response = inventoryService.createInventory(requestDto, productImage);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @GetMapping("/get-all-inventory")
    public ResponseEntity<List<InventoryResponseDto>> getAllInventory() {
        return ResponseEntity.ok(inventoryService.getAllInventory());
    }

    @GetMapping("/get-inventory-by-id/{id}")
    public ResponseEntity<InventoryResponseDto> getInventoryById(@PathVariable Long id) {
        return ResponseEntity.ok(inventoryService.getInventoryById(id));
    }

    @GetMapping("/get-inventory-by-inventory-id/inventory-code/{inventoryId}")
    public ResponseEntity<InventoryResponseDto> getInventoryByInventoryId(@PathVariable String inventoryId) {
        return ResponseEntity.ok(inventoryService.getInventoryByInventoryId(inventoryId));
    }

    @GetMapping("/get-inventory-by-tenant-and-branch/tenant/{tenantId}/branch/{branchId}")
    public ResponseEntity<List<InventoryResponseDto>> getInventoryByTenantAndBranch(
            @PathVariable String tenantId,
            @PathVariable String branchId) {
        return ResponseEntity.ok(inventoryService.getInventoryByTenantAndBranch(tenantId, branchId));
    }

    @GetMapping("/get-inventory-by-stock-status/status/{stockStatus}")
    public ResponseEntity<List<InventoryResponseDto>> getInventoryByStockStatus(@PathVariable String stockStatus) {
        return ResponseEntity.ok(inventoryService.getInventoryByStockStatus(stockStatus));
    }

    @GetMapping("/get-low-stock-inventory/low-stock")
    public ResponseEntity<List<InventoryResponseDto>> getLowStockInventory() {
        return ResponseEntity.ok(inventoryService.getLowStockInventory());
    }

    @PutMapping(value = "/update-inventory/{id}", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<InventoryResponseDto> updateInventory(
            @PathVariable Long id,
            @ModelAttribute InventoryRequestDto requestDto,
            @RequestParam(value = "productImage", required = false) MultipartFile productImage) throws IOException {
        return ResponseEntity.ok(inventoryService.updateInventory(id, requestDto, productImage));
    }

    @PatchMapping(value = "/patch-inventory/{id}", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<InventoryResponseDto> patchInventory(
            @PathVariable Long id,
            @ModelAttribute InventoryRequestDto requestDto,
            @RequestParam(value = "productImage", required = false) MultipartFile productImage) throws IOException {
        return ResponseEntity.ok(inventoryService.patchInventory(id, requestDto, productImage));
    }

    @DeleteMapping("/delete-inventory/{id}")
    public ResponseEntity<Void> deleteInventory(@PathVariable Long id) {
        inventoryService.deleteInventory(id);
        return ResponseEntity.noContent().build();
    }
}