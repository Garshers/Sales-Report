package com.salesreport.salesreport.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.salesreport.salesreport.model.Stock;
import com.salesreport.salesreport.repository.StockRepository;


@Service
public class StockService {
    
    // The @Autowired annotation automatically injects the StockRepository dependency into the StockService class.
    // Spring manages the creation of StockRepository and its injection into StockService.
    @Autowired
    private StockRepository stockRepository;

    public List<Stock> getAllStocks() {
        return stockRepository.findAll();  // Retrieve all Stock objects from the database
    }

    public Stock saveStock(Stock stock) {
        return stockRepository.save(stock);  // Save the Stock object to the database
    }
}
