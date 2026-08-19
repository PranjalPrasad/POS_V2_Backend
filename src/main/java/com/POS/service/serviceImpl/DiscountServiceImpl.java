package com.POS.service.serviceImpl;
import com.POS.dto.requestDto.DiscountRequestDto;
import com.POS.dto.responseDto.DiscountResponseDto;
import com.POS.entity.DiscountEntity;
import com.POS.repository.DiscountRepository;
import com.POS.service.DiscountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

@Service
public class DiscountServiceImpl implements DiscountService {

    private final DiscountRepository discountRepository;

    @Autowired
    public DiscountServiceImpl(DiscountRepository discountRepository) {
        this.discountRepository = discountRepository;
    }

    @Override
    public DiscountResponseDto createDiscount(DiscountRequestDto requestDto) {
        DiscountEntity entity = new DiscountEntity();
        mapDtoToEntity(requestDto, entity);
        if (entity.getUsedCount() == null) entity.setUsedCount(0);
        entity.setCreatedAt(LocalDateTime.now());
        entity.setUpdatedAt(LocalDateTime.now());
        DiscountEntity saved = discountRepository.save(entity);
        return mapEntityToDto(saved);
    }

    @Override
    public List<DiscountResponseDto> getAllDiscounts() {
        return discountRepository.findAll()
                .stream()
                .map(this::mapEntityToDto)
                .collect(Collectors.toList());
    }

    @Override
    public DiscountResponseDto getDiscountById(Long id) {
        DiscountEntity entity = discountRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Discount not found with id: " + id));
        return mapEntityToDto(entity);
    }

    @Override
    public DiscountResponseDto getDiscountByDiscountId(String discountId) {
        DiscountEntity entity = discountRepository.findByDiscountId(discountId)
                .orElseThrow(() -> new NoSuchElementException("Discount not found with discountId: " + discountId));
        return mapEntityToDto(entity);
    }

    @Override
    public DiscountResponseDto getDiscountByCode(String code) {
        DiscountEntity entity = discountRepository.findByCode(code)
                .orElseThrow(() -> new NoSuchElementException("Discount not found with code: " + code));
        return mapEntityToDto(entity);
    }

    @Override
    public List<DiscountResponseDto> getDiscountsByTenantAndBranch(String tenantId, String branchId) {
        return discountRepository.findByTenantIdAndBranchId(tenantId, branchId)
                .stream()
                .map(this::mapEntityToDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<DiscountResponseDto> getActiveDiscounts() {
        return discountRepository.findByIsActive(true)
                .stream()
                .map(this::mapEntityToDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<DiscountResponseDto> getDiscountsByType(String discountType) {
        return discountRepository.findByDiscountType(discountType)
                .stream()
                .map(this::mapEntityToDto)
                .collect(Collectors.toList());
    }

    @Override
    public DiscountResponseDto updateDiscount(Long id, DiscountRequestDto requestDto) {
        DiscountEntity entity = discountRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Discount not found with id: " + id));
        mapDtoToEntity(requestDto, entity);
        entity.setUpdatedAt(LocalDateTime.now());
        DiscountEntity saved = discountRepository.save(entity);
        return mapEntityToDto(saved);
    }

    @Override
    public DiscountResponseDto patchDiscount(Long id, DiscountRequestDto requestDto) {
        DiscountEntity entity = discountRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Discount not found with id: " + id));

        if (requestDto.getDiscountId() != null) entity.setDiscountId(requestDto.getDiscountId());
        if (requestDto.getTenantId() != null) entity.setTenantId(requestDto.getTenantId());
        if (requestDto.getBranchId() != null) entity.setBranchId(requestDto.getBranchId());

        if (requestDto.getName() != null) entity.setName(requestDto.getName());
        if (requestDto.getCode() != null) entity.setCode(requestDto.getCode());
        if (requestDto.getDescription() != null) entity.setDescription(requestDto.getDescription());

        if (requestDto.getDiscountType() != null) entity.setDiscountType(requestDto.getDiscountType());
        if (requestDto.getDiscountValue() != null) entity.setDiscountValue(requestDto.getDiscountValue());
        if (requestDto.getMinimumOrderAmount() != null) entity.setMinimumOrderAmount(requestDto.getMinimumOrderAmount());
        if (requestDto.getMaximumDiscountAmount() != null) entity.setMaximumDiscountAmount(requestDto.getMaximumDiscountAmount());

        // arrays: if sent, fully replace (no partial-item patch inside an array)
        if (requestDto.getProducts() != null) {
            entity.getProducts().clear();
            entity.getProducts().addAll(requestDto.getProducts());
        }
        if (requestDto.getCategories() != null) {
            entity.getCategories().clear();
            entity.getCategories().addAll(requestDto.getCategories());
        }
        if (requestDto.getCustomerGroups() != null) {
            entity.getCustomerGroups().clear();
            entity.getCustomerGroups().addAll(requestDto.getCustomerGroups());
        }

        if (requestDto.getStartDate() != null) entity.setStartDate(requestDto.getStartDate());
        if (requestDto.getEndDate() != null) entity.setEndDate(requestDto.getEndDate());

        if (requestDto.getUsageLimit() != null) entity.setUsageLimit(requestDto.getUsageLimit());
        if (requestDto.getUsedCount() != null) entity.setUsedCount(requestDto.getUsedCount());

        if (requestDto.getIsActive() != null) entity.setIsActive(requestDto.getIsActive());

        entity.setUpdatedAt(LocalDateTime.now());

        DiscountEntity saved = discountRepository.save(entity);
        return mapEntityToDto(saved);
    }

    @Override
    public DiscountResponseDto redeemDiscount(Long id) {
        DiscountEntity entity = discountRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Discount not found with id: " + id));

        int currentUsed = entity.getUsedCount() != null ? entity.getUsedCount() : 0;
        entity.setUsedCount(currentUsed + 1);

        if (entity.getUsageLimit() != null && entity.getUsedCount() >= entity.getUsageLimit()) {
            entity.setIsActive(false);
        }

        entity.setUpdatedAt(LocalDateTime.now());

        DiscountEntity saved = discountRepository.save(entity);
        return mapEntityToDto(saved);
    }

    @Override
    public void deleteDiscount(Long id) {
        if (!discountRepository.existsById(id)) {
            throw new NoSuchElementException("Discount not found with id: " + id);
        }
        discountRepository.deleteById(id);
    }

    // ---------------- helper / mapping methods ----------------

    private void mapDtoToEntity(DiscountRequestDto dto, DiscountEntity entity) {
        entity.setDiscountId(dto.getDiscountId());
        entity.setTenantId(dto.getTenantId());
        entity.setBranchId(dto.getBranchId());

        entity.setName(dto.getName());
        entity.setCode(dto.getCode());
        entity.setDescription(dto.getDescription());

        entity.setDiscountType(dto.getDiscountType());
        entity.setDiscountValue(dto.getDiscountValue());
        entity.setMinimumOrderAmount(dto.getMinimumOrderAmount());
        entity.setMaximumDiscountAmount(dto.getMaximumDiscountAmount());

        entity.getProducts().clear();
        if (dto.getProducts() != null) entity.getProducts().addAll(dto.getProducts());

        entity.getCategories().clear();
        if (dto.getCategories() != null) entity.getCategories().addAll(dto.getCategories());

        entity.getCustomerGroups().clear();
        if (dto.getCustomerGroups() != null) entity.getCustomerGroups().addAll(dto.getCustomerGroups());

        entity.setStartDate(dto.getStartDate());
        entity.setEndDate(dto.getEndDate());

        entity.setUsageLimit(dto.getUsageLimit());
        entity.setUsedCount(dto.getUsedCount());

        entity.setIsActive(dto.getIsActive());
    }

    private DiscountResponseDto mapEntityToDto(DiscountEntity entity) {
        DiscountResponseDto dto = new DiscountResponseDto();
        dto.setId(entity.getId());
        dto.setDiscountId(entity.getDiscountId());
        dto.setTenantId(entity.getTenantId());
        dto.setBranchId(entity.getBranchId());

        dto.setName(entity.getName());
        dto.setCode(entity.getCode());
        dto.setDescription(entity.getDescription());

        dto.setDiscountType(entity.getDiscountType());
        dto.setDiscountValue(entity.getDiscountValue());
        dto.setMinimumOrderAmount(entity.getMinimumOrderAmount());
        dto.setMaximumDiscountAmount(entity.getMaximumDiscountAmount());

        dto.setProducts(new ArrayList<>(entity.getProducts()));
        dto.setCategories(new ArrayList<>(entity.getCategories()));
        dto.setCustomerGroups(new ArrayList<>(entity.getCustomerGroups()));

        dto.setStartDate(entity.getStartDate());
        dto.setEndDate(entity.getEndDate());

        dto.setUsageLimit(entity.getUsageLimit());
        dto.setUsedCount(entity.getUsedCount());

        dto.setIsActive(entity.getIsActive());
        dto.setCreatedAt(entity.getCreatedAt());
        dto.setUpdatedAt(entity.getUpdatedAt());
        return dto;
    }
}
