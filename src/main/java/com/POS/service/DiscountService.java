package com.POS.service;


import com.POS.dto.requestDto.DiscountRequestDto;
import com.POS.dto.responseDto.DiscountResponseDto;

import java.util.List;

public interface DiscountService {

    DiscountResponseDto createDiscount(DiscountRequestDto requestDto);

    List<DiscountResponseDto> getAllDiscounts();

    DiscountResponseDto getDiscountById(Long id);

    DiscountResponseDto getDiscountByDiscountId(String discountId);

    DiscountResponseDto getDiscountByCode(String code);

    List<DiscountResponseDto> getDiscountsByTenantAndBranch(String tenantId, String branchId);

    List<DiscountResponseDto> getActiveDiscounts();

    List<DiscountResponseDto> getDiscountsByType(String discountType);

    // Full update - all fields + full arrays expected
    DiscountResponseDto updateDiscount(Long id, DiscountRequestDto requestDto);

    // Partial update - only non-null top-level fields applied;
    // any array sent (products/categories/customerGroups) fully replaces the existing one
    DiscountResponseDto patchDiscount(Long id, DiscountRequestDto requestDto);

    // Convenience: increment usedCount by 1 (call this when a discount is applied to an order)
    DiscountResponseDto redeemDiscount(Long id);

    void deleteDiscount(Long id);
}
