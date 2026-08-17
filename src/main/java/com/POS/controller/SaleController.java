package com.POS.controller;

import com.POS.dto.requestDto.PaymentUpdateRequestDto;
import com.POS.dto.requestDto.SalePatchRequestDto;
import com.POS.dto.requestDto.SaleRequestDto;
import com.POS.dto.resposneDto.SaleResponseDto;
import com.POS.service.SaleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.List;

@RestController
@RequestMapping("/api/v1/sales")
public class SaleController {

    private final SaleService saleService;

    @Autowired
    public SaleController(SaleService saleService) {
        this.saleService = saleService;
    }

    // Create sale
    @PostMapping
    public ResponseEntity<SaleResponseDto> createSale(@RequestBody SaleRequestDto requestDto) {
        SaleResponseDto response = saleService.createSale(requestDto);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    // Get sale by saleId
    @GetMapping("/{saleId}")
    public ResponseEntity<SaleResponseDto> getSaleById(@PathVariable String saleId) {
        return ResponseEntity.ok(saleService.getSaleById(saleId));
    }

    // Get sale by invoice number
    @GetMapping("/number/{saleNumber}")
    public ResponseEntity<SaleResponseDto> getSaleByNumber(@PathVariable String saleNumber) {
        return ResponseEntity.ok(saleService.getSaleByNumber(saleNumber));
    }

    // Get all sales
    @GetMapping
    public ResponseEntity<List<SaleResponseDto>> getAllSales() {
        return ResponseEntity.ok(saleService.getAllSales());
    }

    // Get sales by customer
    @GetMapping("/customer/{customerId}")
    public ResponseEntity<List<SaleResponseDto>> getSalesByCustomer(@PathVariable String customerId) {
        return ResponseEntity.ok(saleService.getSalesByCustomer(customerId));
    }

    // Update status only
    @PutMapping("/{saleId}/status")
    public ResponseEntity<SaleResponseDto> updateStatus(@PathVariable String saleId,
                                                        @RequestParam String status) {
        return ResponseEntity.ok(saleService.updateSaleStatus(saleId, status));
    }

    // Update payment info
    @PutMapping("/{saleId}/payment")
    public ResponseEntity<SaleResponseDto> updatePayment(@PathVariable String saleId,
                                                         @RequestBody PaymentUpdateRequestDto paymentDto) {
        return ResponseEntity.ok(saleService.updatePayment(saleId, paymentDto));
    }

    // Partial update sale (only sent fields update honge)
    @PatchMapping("/{saleId}")
    public ResponseEntity<SaleResponseDto> patchSale(@PathVariable String saleId,
                                                     @RequestBody SalePatchRequestDto patchDto) {
        return ResponseEntity.ok(saleService.patchSale(saleId, patchDto));
    }

    // Delete sale
    @DeleteMapping("/{saleId}")
    public ResponseEntity<Void> deleteSale(@PathVariable String saleId) {
        saleService.deleteSale(saleId);
        return ResponseEntity.noContent().build();
    }
}
