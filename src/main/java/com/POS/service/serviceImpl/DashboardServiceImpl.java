package com.POS.service.serviceImpl;


import com.POS.dto.responseDto.DashboardResponseDto;
import com.POS.repository.CustomerRepository;
import com.POS.service.DashboardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;


@Service
public class DashboardServiceImpl implements DashboardService {

    private final CustomerRepository customerRepository;

    // ADJUST: uncomment once imports above are fixed
    // private final SaleRepository saleRepository;
    // private final ProductRepository productRepository;
    // private final InventoryRepository inventoryRepository;

    @Autowired
    public DashboardServiceImpl(CustomerRepository customerRepository
                                // ADJUST: add these back once repos are wired
                                // , SaleRepository saleRepository
                                // , ProductRepository productRepository
                                // , InventoryRepository inventoryRepository
    ) {
        this.customerRepository = customerRepository;
        // this.saleRepository = saleRepository;
        // this.productRepository = productRepository;
        // this.inventoryRepository = inventoryRepository;
    }

    @Override
    public DashboardResponseDto getDashboardSummary() {

        DashboardResponseDto response = new DashboardResponseDto();

        // ---------- Total customers (this part works out of the box) ----------
        response.setTotalCustomers(customerRepository.count());

        // ---------- Sales today (total revenue + order count) ----------
        // ADJUST: uncomment and fix once SaleRepository/SaleEntity are wired in.
        /*
        LocalDate today = LocalDate.now();
        List<SaleEntity> allSales = saleRepository.findAll();

        List<SaleEntity> todaysSales = new ArrayList<>();
        for (SaleEntity sale : allSales) {
            if (sale.getSaleDate() != null && sale.getSaleDate().toLocalDate().isEqual(today)) {
                todaysSales.add(sale);
            }
        }

        double totalSalesToday = 0.0;
        for (SaleEntity sale : todaysSales) {
            if (sale.getTotalAmount() != null) {
                totalSalesToday += sale.getTotalAmount();
            }
        }
        response.setTotalSalesToday(totalSalesToday);
        response.setTotalOrdersToday(todaysSales.size());
        */
        // Placeholder defaults until SaleRepository is wired in:
        response.setTotalSalesToday(0.0);
        response.setTotalOrdersToday(0);

        // ---------- Low stock items count ----------
        // ADJUST: uncomment and fix once InventoryRepository/InventoryEntity are wired in.
        /*
        List<InventoryEntity> allInventory = inventoryRepository.findAll();
        long lowStockCount = 0;
        for (InventoryEntity inv : allInventory) {
            if (inv.getStockQuantity() != null && inv.getReorderLevel() != null
                    && inv.getStockQuantity() <= inv.getReorderLevel()) {
                lowStockCount++;
            }
        }
        response.setLowStockItemsCount(lowStockCount);
        */
        response.setLowStockItemsCount(0L);

        // ---------- Active tables count ----------
        // No TableRepository was mentioned in your repo list — if you have a
        // restaurant-tables module, inject its repository and count tables with
        // status = "occupied"/"active" here. Hardcoded for now.
        response.setActiveTablesCount(0);

        // ---------- Top selling products ----------
        // ADJUST: this needs sale-item-level data (productId + quantity per sale).
        // If your SaleEntity has a List<SaleItemEntity> (via @OneToMany), aggregate
        // like this:
        /*
        Map<String, TopSellingProductDto> productSalesMap = new HashMap<>();
        for (SaleEntity sale : allSales) {
            for (SaleItemEntity item : sale.getSaleItems()) {
                String pid = item.getProductId();
                TopSellingProductDto existing = productSalesMap.get(pid);
                if (existing == null) {
                    productSalesMap.put(pid, new TopSellingProductDto(pid, item.getProductName(), item.getQuantity()));
                } else {
                    existing.setUnitsSold(existing.getUnitsSold() + item.getQuantity());
                }
            }
        }
        List<TopSellingProductDto> topProducts = new ArrayList<>(productSalesMap.values());
        topProducts.sort(Comparator.comparing(TopSellingProductDto::getUnitsSold).reversed());
        if (topProducts.size() > 5) {
            topProducts = topProducts.subList(0, 5);
        }
        response.setTopSellingProducts(topProducts);
        */
        response.setTopSellingProducts(new ArrayList<>());

        // ---------- Recent orders (latest 5 sales) ----------
        // ADJUST: uncomment and fix once SaleRepository/SaleEntity are wired in.
        /*
        List<SaleEntity> sortedSales = new ArrayList<>(allSales);
        sortedSales.sort(Comparator.comparing(SaleEntity::getSaleDate).reversed());
        List<RecentOrderDto> recentOrders = new ArrayList<>();
        int limit = Math.min(5, sortedSales.size());
        for (int i = 0; i < limit; i++) {
            SaleEntity sale = sortedSales.get(i);
            recentOrders.add(new RecentOrderDto(
                    sale.getSaleId(),
                    sale.getCustomerName(),
                    sale.getTotalAmount(),
                    sale.getStatus()
            ));
        }
        response.setRecentOrders(recentOrders);
        */
        response.setRecentOrders(new ArrayList<>());

        return response;
    }
}
