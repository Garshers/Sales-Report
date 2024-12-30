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

    // Endpoint for HTML view
    @GetMapping("/sales-report")
    public String getSalesReport(Model model) {
        List<Sale> sales = saleService.getAllSales();
        model.addAttribute("sales", sales); // Send data to view
        return "sales"; // HTML Template name (sales.html)
    }
}
