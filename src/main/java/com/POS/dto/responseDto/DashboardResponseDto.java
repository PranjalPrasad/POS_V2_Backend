package com.POS.dto.responseDto;


import com.POS.dto.requestDto.RecentOrderDto;
import com.POS.dto.requestDto.TopSellingProductDto;

import java.util.List;

public class DashboardResponseDto {

    private Double totalSalesToday;
    private Integer totalOrdersToday;
    private Long totalCustomers;
    private Long lowStockItemsCount;
    private Integer activeTablesCount;
    private List<TopSellingProductDto> topSellingProducts;
    private List<RecentOrderDto> recentOrders;

    public DashboardResponseDto() {
    }

    public DashboardResponseDto(Double totalSalesToday, Integer totalOrdersToday, Long totalCustomers,
                                Long lowStockItemsCount, Integer activeTablesCount,
                                List<TopSellingProductDto> topSellingProducts,
                                List<RecentOrderDto> recentOrders) {
        this.totalSalesToday = totalSalesToday;
        this.totalOrdersToday = totalOrdersToday;
        this.totalCustomers = totalCustomers;
        this.lowStockItemsCount = lowStockItemsCount;
        this.activeTablesCount = activeTablesCount;
        this.topSellingProducts = topSellingProducts;
        this.recentOrders = recentOrders;
    }

    public Double getTotalSalesToday() {
        return totalSalesToday;
    }

    public void setTotalSalesToday(Double totalSalesToday) {
        this.totalSalesToday = totalSalesToday;
    }

    public Integer getTotalOrdersToday() {
        return totalOrdersToday;
    }

    public void setTotalOrdersToday(Integer totalOrdersToday) {
        this.totalOrdersToday = totalOrdersToday;
    }

    public Long getTotalCustomers() {
        return totalCustomers;
    }

    public void setTotalCustomers(Long totalCustomers) {
        this.totalCustomers = totalCustomers;
    }

    public Long getLowStockItemsCount() {
        return lowStockItemsCount;
    }

    public void setLowStockItemsCount(Long lowStockItemsCount) {
        this.lowStockItemsCount = lowStockItemsCount;
    }

    public Integer getActiveTablesCount() {
        return activeTablesCount;
    }

    public void setActiveTablesCount(Integer activeTablesCount) {
        this.activeTablesCount = activeTablesCount;
    }

    public List<TopSellingProductDto> getTopSellingProducts() {
        return topSellingProducts;
    }

    public void setTopSellingProducts(List<TopSellingProductDto> topSellingProducts) {
        this.topSellingProducts = topSellingProducts;
    }

    public List<RecentOrderDto> getRecentOrders() {
        return recentOrders;
    }

    public void setRecentOrders(List<RecentOrderDto> recentOrders) {
        this.recentOrders = recentOrders;
    }
}
