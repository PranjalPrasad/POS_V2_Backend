package com.POS.controller;

import com.POS.dto.requestDto.DiscountRequestDto;
import com.POS.dto.responseDto.DiscountResponseDto;
import com.POS.service.DiscountService;
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
@RequestMapping("/api/v1/discounts")
public class DiscountController {

    private final DiscountService discountService;

    @Autowired
    public DiscountController(DiscountService discountService) {
        this.discountService = discountService;
    }

    @PostMapping
    public ResponseEntity<DiscountResponseDto> createDiscount(@RequestBody DiscountRequestDto requestDto) {
        DiscountResponseDto response = discountService.createDiscount(requestDto);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<DiscountResponseDto>> getAllDiscounts() {
        return ResponseEntity.ok(discountService.getAllDiscounts());
    }

    @GetMapping("/{id}")
    public ResponseEntity<DiscountResponseDto> getDiscountById(@PathVariable Long id) {
        return ResponseEntity.ok(discountService.getDiscountById(id));
    }

    @GetMapping("/discount-code/{discountId}")
    public ResponseEntity<DiscountResponseDto> getDiscountByDiscountId(@PathVariable String discountId) {
        return ResponseEntity.ok(discountService.getDiscountByDiscountId(discountId));
    }

    @GetMapping("/coupon/{code}")
    public ResponseEntity<DiscountResponseDto> getDiscountByCode(@PathVariable String code) {
        return ResponseEntity.ok(discountService.getDiscountByCode(code));
    }

    @GetMapping("/tenant/{tenantId}/branch/{branchId}")
    public ResponseEntity<List<DiscountResponseDto>> getDiscountsByTenantAndBranch(
            @PathVariable String tenantId,
            @PathVariable String branchId) {
        return ResponseEntity.ok(discountService.getDiscountsByTenantAndBranch(tenantId, branchId));
    }

    @GetMapping("/active")
    public ResponseEntity<List<DiscountResponseDto>> getActiveDiscounts() {
        return ResponseEntity.ok(discountService.getActiveDiscounts());
    }

    @GetMapping("/type/{discountType}")
    public ResponseEntity<List<DiscountResponseDto>> getDiscountsByType(@PathVariable String discountType) {
        return ResponseEntity.ok(discountService.getDiscountsByType(discountType));
    }

    @PutMapping("/{id}")
    public ResponseEntity<DiscountResponseDto> updateDiscount(
            @PathVariable Long id,
            @RequestBody DiscountRequestDto requestDto) {
        return ResponseEntity.ok(discountService.updateDiscount(id, requestDto));
    }

    @PatchMapping("/{id}")
    public ResponseEntity<DiscountResponseDto> patchDiscount(
            @PathVariable Long id,
            @RequestBody DiscountRequestDto requestDto) {
        return ResponseEntity.ok(discountService.patchDiscount(id, requestDto));
    }

    @PatchMapping("/{id}/redeem")
    public ResponseEntity<DiscountResponseDto> redeemDiscount(@PathVariable Long id) {
        return ResponseEntity.ok(discountService.redeemDiscount(id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteDiscount(@PathVariable Long id) {
        discountService.deleteDiscount(id);
        return ResponseEntity.noContent().build();
    }
}
