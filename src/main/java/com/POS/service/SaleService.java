package com.POS.service;


import com.POS.dto.requestDto.PaymentUpdateRequestDto;
import com.POS.dto.requestDto.SalePatchRequestDto;
import com.POS.dto.requestDto.SaleRequestDto;
import com.POS.dto.resposneDto.SaleResponseDto;

import java.util.List;

public interface SaleService {

    SaleResponseDto createSale(SaleRequestDto requestDto);

    SaleResponseDto getSaleById(String saleId);

    SaleResponseDto getSaleByNumber(String saleNumber);

    List<SaleResponseDto> getAllSales();

    List<SaleResponseDto> getSalesByCustomer(String customerId);

    SaleResponseDto updateSaleStatus(String saleId, String status);

    SaleResponseDto updatePayment(String saleId, PaymentUpdateRequestDto paymentDto);

    void deleteSale(String saleId);

    SaleResponseDto patchSale(String saleId, SalePatchRequestDto patchDto);
}
