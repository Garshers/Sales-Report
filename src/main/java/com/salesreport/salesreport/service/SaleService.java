package com.salesreport.salesreport.service;

import com.salesreport.salesreport.model.Sale;
import com.salesreport.salesreport.repository.SaleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SaleService {
    @Autowired
    private SaleRepository saleRepository;

    // Method to get all sales data
    public List<Sale> getAllSales() {
        return saleRepository.findAll();
    }

    // Method to save new sale data
    public Sale saveSale(Sale sale) {
        return saleRepository.save(sale);
    }

}
