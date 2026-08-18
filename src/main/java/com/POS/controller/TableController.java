package com.POS.controller;

import com.POS.dto.requestDto.TableRequestDto;
import com.POS.dto.resposneDto.TableResponseDto;
import com.POS.service.TableService;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/tables")
public class TableController {

    private final TableService tableService;

    @Autowired
    public TableController(TableService tableService) {
        this.tableService = tableService;
    }

    @PostMapping("/create-table")
    public ResponseEntity<TableResponseDto> createTable(@RequestBody TableRequestDto requestDto) {
        TableResponseDto response = tableService.createTable(requestDto);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @GetMapping("/get-all-tables")
    public ResponseEntity<List<TableResponseDto>> getAllTables() {
        return ResponseEntity.ok(tableService.getAllTables());
    }

    @GetMapping("/get-table-by-id/{id}")
    public ResponseEntity<TableResponseDto> getTableById(@PathVariable Long id) {
        return ResponseEntity.ok(tableService.getTableById(id));
    }

    @GetMapping("/get-table-by-table-id/table-code/{tableId}")
    public ResponseEntity<TableResponseDto> getTableByTableId(@PathVariable String tableId) {
        return ResponseEntity.ok(tableService.getTableByTableId(tableId));
    }

    @GetMapping("/get-tables-by-tenant-and-branch/tenant/{tenantId}/branch/{branchId}")
    public ResponseEntity<List<TableResponseDto>> getTablesByTenantAndBranch(
            @PathVariable String tenantId,
            @PathVariable String branchId) {
        return ResponseEntity.ok(tableService.getTablesByTenantAndBranch(tenantId, branchId));
    }

    @GetMapping("/get-tables-by-status/status/{currentStatus}")
    public ResponseEntity<List<TableResponseDto>> getTablesByStatus(@PathVariable String currentStatus) {
        return ResponseEntity.ok(tableService.getTablesByStatus(currentStatus));
    }

    @GetMapping("/get-tables-by-section/section/{sectionId}")
    public ResponseEntity<List<TableResponseDto>> getTablesBySection(@PathVariable String sectionId) {
        return ResponseEntity.ok(tableService.getTablesBySection(sectionId));
    }

    @GetMapping("/get-active-tables/active")
    public ResponseEntity<List<TableResponseDto>> getActiveTables() {
        return ResponseEntity.ok(tableService.getActiveTables());
    }

    @PutMapping("/update-table/{id}")
    public ResponseEntity<TableResponseDto> updateTable(
            @PathVariable Long id,
            @RequestBody TableRequestDto requestDto) {
        return ResponseEntity.ok(tableService.updateTable(id, requestDto));
    }

    @PatchMapping("/patch-table/{id}")
    public ResponseEntity<TableResponseDto> patchTable(
            @PathVariable Long id,
            @RequestBody TableRequestDto requestDto) {
        return ResponseEntity.ok(tableService.patchTable(id, requestDto));
    }

    @PatchMapping("/assign-order/{id}/assign-order")
    public ResponseEntity<TableResponseDto> assignOrder(
            @PathVariable Long id,
            @RequestParam String orderId) {
        return ResponseEntity.ok(tableService.assignOrder(id, orderId));
    }

    @PatchMapping("/clear-order/{id}/clear-order")
    public ResponseEntity<TableResponseDto> clearOrder(@PathVariable Long id) {
        return ResponseEntity.ok(tableService.clearOrder(id));
    }

    @DeleteMapping("/delete-table/{id}")
    public ResponseEntity<Void> deleteTable(@PathVariable Long id) {
        tableService.deleteTable(id);
        return ResponseEntity.noContent().build();
    }
}
