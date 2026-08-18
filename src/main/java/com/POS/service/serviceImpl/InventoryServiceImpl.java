package com.POS.service.serviceImpl;

import com.POS.dto.requestDto.InventoryRequestDto;
import com.POS.dto.resposneDto.InventoryResponseDto;
import com.POS.entity.InventoryEntity;
import com.POS.repository.InventoryRepository;
import com.POS.service.InventoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

@Service
public class InventoryServiceImpl implements InventoryService {

    private final InventoryRepository inventoryRepository;

    @Autowired
    public InventoryServiceImpl(InventoryRepository inventoryRepository) {
        this.inventoryRepository = inventoryRepository;
    }

    @Override
    public InventoryResponseDto createInventory(InventoryRequestDto requestDto) {
        InventoryEntity entity = new InventoryEntity();
        mapDtoToEntity(requestDto, entity);
        entity.setCreatedAt(LocalDateTime.now());
        entity.setUpdatedAt(LocalDateTime.now());
        recalculateDerivedFields(entity);
        InventoryEntity saved = inventoryRepository.save(entity);
        return mapEntityToDto(saved);
    }

    @Override
    public List<InventoryResponseDto> getAllInventory() {
        return inventoryRepository.findAll()
                .stream()
                .map(this::mapEntityToDto)
                .collect(Collectors.toList());
    }

    @Override
    public InventoryResponseDto getInventoryById(Long id) {
        InventoryEntity entity = inventoryRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Inventory not found with id: " + id));
        return mapEntityToDto(entity);
    }

    @Override
    public InventoryResponseDto getInventoryByInventoryId(String inventoryId) {
        InventoryEntity entity = inventoryRepository.findByInventoryId(inventoryId)
                .orElseThrow(() -> new NoSuchElementException("Inventory not found with inventoryId: " + inventoryId));
        return mapEntityToDto(entity);
    }

    @Override
    public List<InventoryResponseDto> getInventoryByTenantAndBranch(String tenantId, String branchId) {
        return inventoryRepository.findByTenantIdAndBranchId(tenantId, branchId)
                .stream()
                .map(this::mapEntityToDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<InventoryResponseDto> getInventoryByStockStatus(String stockStatus) {
        return inventoryRepository.findByStockStatus(stockStatus)
                .stream()
                .map(this::mapEntityToDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<InventoryResponseDto> getLowStockInventory() {
        return inventoryRepository.findAll()
                .stream()
                .filter(e -> e.getCurrentStock() != null
                        && e.getReorderLevel() != null
                        && e.getCurrentStock() <= e.getReorderLevel())
                .map(this::mapEntityToDto)
                .collect(Collectors.toList());
    }

    @Override
    public InventoryResponseDto updateInventory(Long id, InventoryRequestDto requestDto) {
        InventoryEntity entity = inventoryRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Inventory not found with id: " + id));
        mapDtoToEntity(requestDto, entity);
        entity.setUpdatedAt(LocalDateTime.now());
        recalculateDerivedFields(entity);
        InventoryEntity saved = inventoryRepository.save(entity);
        return mapEntityToDto(saved);
    }

    @Override
    public InventoryResponseDto patchInventory(Long id, InventoryRequestDto requestDto) {
        InventoryEntity entity = inventoryRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Inventory not found with id: " + id));

        if (requestDto.getInventoryId() != null) entity.setInventoryId(requestDto.getInventoryId());
        if (requestDto.getTenantId() != null) entity.setTenantId(requestDto.getTenantId());
        if (requestDto.getBranchId() != null) entity.setBranchId(requestDto.getBranchId());

        if (requestDto.getProductId() != null) entity.setProductId(requestDto.getProductId());
        if (requestDto.getProductName() != null) entity.setProductName(requestDto.getProductName());
        if (requestDto.getProductSku() != null) entity.setProductSku(requestDto.getProductSku());

        if (requestDto.getWarehouseId() != null) entity.setWarehouseId(requestDto.getWarehouseId());
        if (requestDto.getWarehouseName() != null) entity.setWarehouseName(requestDto.getWarehouseName());

        if (requestDto.getOpeningStock() != null) entity.setOpeningStock(requestDto.getOpeningStock());
        if (requestDto.getReceivedStock() != null) entity.setReceivedStock(requestDto.getReceivedStock());
        if (requestDto.getIssuedStock() != null) entity.setIssuedStock(requestDto.getIssuedStock());
        if (requestDto.getAdjustedStock() != null) entity.setAdjustedStock(requestDto.getAdjustedStock());
        if (requestDto.getCurrentStock() != null) entity.setCurrentStock(requestDto.getCurrentStock());
        if (requestDto.getReservedStock() != null) entity.setReservedStock(requestDto.getReservedStock());
        if (requestDto.getAvailableStock() != null) entity.setAvailableStock(requestDto.getAvailableStock());

        if (requestDto.getUnitId() != null) entity.setUnitId(requestDto.getUnitId());
        if (requestDto.getUnitName() != null) entity.setUnitName(requestDto.getUnitName());
        if (requestDto.getUnitCode() != null) entity.setUnitCode(requestDto.getUnitCode());

        if (requestDto.getMinimumLevel() != null) entity.setMinimumLevel(requestDto.getMinimumLevel());
        if (requestDto.getReorderLevel() != null) entity.setReorderLevel(requestDto.getReorderLevel());
        if (requestDto.getReorderQuantity() != null) entity.setReorderQuantity(requestDto.getReorderQuantity());

        if (requestDto.getStockStatus() != null) entity.setStockStatus(requestDto.getStockStatus());

        entity.setUpdatedAt(LocalDateTime.now());
        recalculateDerivedFields(entity);

        InventoryEntity saved = inventoryRepository.save(entity);
        return mapEntityToDto(saved);
    }

    @Override
    public void deleteInventory(Long id) {
        if (!inventoryRepository.existsById(id)) {
            throw new NoSuchElementException("Inventory not found with id: " + id);
        }
        inventoryRepository.deleteById(id);
    }

    // ---------------- helper / mapping methods ----------------

    private void mapDtoToEntity(InventoryRequestDto dto, InventoryEntity entity) {
        entity.setInventoryId(dto.getInventoryId());
        entity.setTenantId(dto.getTenantId());
        entity.setBranchId(dto.getBranchId());

        entity.setProductId(dto.getProductId());
        entity.setProductName(dto.getProductName());
        entity.setProductSku(dto.getProductSku());

        entity.setWarehouseId(dto.getWarehouseId());
        entity.setWarehouseName(dto.getWarehouseName());

        entity.setOpeningStock(dto.getOpeningStock());
        entity.setReceivedStock(dto.getReceivedStock());
        entity.setIssuedStock(dto.getIssuedStock());
        entity.setAdjustedStock(dto.getAdjustedStock());
        entity.setCurrentStock(dto.getCurrentStock());
        entity.setReservedStock(dto.getReservedStock());
        entity.setAvailableStock(dto.getAvailableStock());

        entity.setUnitId(dto.getUnitId());
        entity.setUnitName(dto.getUnitName());
        entity.setUnitCode(dto.getUnitCode());

        entity.setMinimumLevel(dto.getMinimumLevel());
        entity.setReorderLevel(dto.getReorderLevel());
        entity.setReorderQuantity(dto.getReorderQuantity());

        entity.setStockStatus(dto.getStockStatus());
    }

    private InventoryResponseDto mapEntityToDto(InventoryEntity entity) {
        InventoryResponseDto dto = new InventoryResponseDto();
        dto.setId(entity.getId());
        dto.setInventoryId(entity.getInventoryId());
        dto.setTenantId(entity.getTenantId());
        dto.setBranchId(entity.getBranchId());

        dto.setProductId(entity.getProductId());
        dto.setProductName(entity.getProductName());
        dto.setProductSku(entity.getProductSku());

        dto.setWarehouseId(entity.getWarehouseId());
        dto.setWarehouseName(entity.getWarehouseName());

        dto.setOpeningStock(entity.getOpeningStock());
        dto.setReceivedStock(entity.getReceivedStock());
        dto.setIssuedStock(entity.getIssuedStock());
        dto.setAdjustedStock(entity.getAdjustedStock());
        dto.setCurrentStock(entity.getCurrentStock());
        dto.setReservedStock(entity.getReservedStock());
        dto.setAvailableStock(entity.getAvailableStock());

        dto.setUnitId(entity.getUnitId());
        dto.setUnitName(entity.getUnitName());
        dto.setUnitCode(entity.getUnitCode());

        dto.setMinimumLevel(entity.getMinimumLevel());
        dto.setReorderLevel(entity.getReorderLevel());
        dto.setReorderQuantity(entity.getReorderQuantity());

        dto.setStockStatus(entity.getStockStatus());
        dto.setCreatedAt(entity.getCreatedAt());
        dto.setUpdatedAt(entity.getUpdatedAt());
        return dto;
    }

    private void recalculateDerivedFields(InventoryEntity entity) {
        if (entity.getCurrentStock() != null && entity.getReservedStock() != null) {
            entity.setAvailableStock(entity.getCurrentStock() - entity.getReservedStock());
        }

        if (entity.getCurrentStock() != null && entity.getMinimumLevel() != null
                && entity.getReorderLevel() != null) {
            int current = entity.getCurrentStock();
            if (current <= entity.getMinimumLevel()) {
                entity.setStockStatus("OUT_OF_STOCK");
            } else if (current <= entity.getReorderLevel()) {
                entity.setStockStatus("LOW_STOCK");
            } else {
                entity.setStockStatus("IN_STOCK");
            }
        }
    }
}
