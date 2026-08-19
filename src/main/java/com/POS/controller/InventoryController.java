package com.POS.controller;

import com.POS.dto.requestDto.InventoryRequestDto;
import com.POS.dto.responseDto.InventoryResponseDto;
import com.POS.service.InventoryService;
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
@RequestMapping("/api/v1/inventory")
public class InventoryController {

    private final InventoryService inventoryService;

    @Autowired
    public InventoryController(InventoryService inventoryService) {
        this.inventoryService = inventoryService;
    }

    @PostMapping("/create-inventory")
    public ResponseEntity<InventoryResponseDto> createInventory(@RequestBody InventoryRequestDto requestDto) {
        InventoryResponseDto response = inventoryService.createInventory(requestDto);
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

    @PutMapping("/update-inventory/{id}")
    public ResponseEntity<InventoryResponseDto> updateInventory(
            @PathVariable Long id,
            @RequestBody InventoryRequestDto requestDto) {
        return ResponseEntity.ok(inventoryService.updateInventory(id, requestDto));
    }

    @PatchMapping("/patch-inventory/{id}")
    public ResponseEntity<InventoryResponseDto> patchInventory(
            @PathVariable Long id,
            @RequestBody InventoryRequestDto requestDto) {
        return ResponseEntity.ok(inventoryService.patchInventory(id, requestDto));
    }

    @DeleteMapping("/delete-inventory/{id}")
    public ResponseEntity<Void> deleteInventory(@PathVariable Long id) {
        inventoryService.deleteInventory(id);
        return ResponseEntity.noContent().build();
    }
}
