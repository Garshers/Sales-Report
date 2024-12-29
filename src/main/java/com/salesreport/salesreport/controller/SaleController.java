package com.salesreport.salesreport.controller;

import com.salesreport.salesreport.model.Sale;
import com.salesreport.salesreport.service.SaleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class SaleController {
    @Autowired
    private SaleService saleService;

    // Endpoint for getting all sales
    @GetMapping("/sales")
    public List<Sale> getSales() {
        return saleService.getAllSales();
    }

    // Endpoint for adding new sales
    @PostMapping("/sales")
    public Sale addSale(@RequestBody Sale sale) {
        return saleService.saveSale(sale);
    }
}
