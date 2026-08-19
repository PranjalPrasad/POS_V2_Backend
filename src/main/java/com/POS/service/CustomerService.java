package com.POS.service;

import com.POS.dto.requestDto.CustomerRequestDto;
import com.POS.dto.responseDto.CustomerResponseDto;

import java.util.List;

public interface CustomerService {

    CustomerResponseDto createCustomer(CustomerRequestDto requestDto);

    List<CustomerResponseDto> getAllCustomers();

    CustomerResponseDto getCustomerById(String customerId);

    List<CustomerResponseDto> getCustomersByTenantAndBranch(String tenantId, String branchId);

    List<CustomerResponseDto> getCustomersByStatus(String status);

    CustomerResponseDto updateCustomer(String customerId, CustomerRequestDto requestDto);

    CustomerResponseDto patchCustomer(String customerId, CustomerRequestDto requestDto);

    void deleteCustomer(String customerId);
}
