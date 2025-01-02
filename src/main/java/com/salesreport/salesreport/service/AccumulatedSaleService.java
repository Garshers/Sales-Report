package com.salesreport.salesreport.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.salesreport.salesreport.model.AccumulatedSale;
import com.salesreport.salesreport.repository.AccumulatedSaleRepository;

// The @Service annotation indicates that this class is a service component in the Spring application.
// The service layer is responsible for business logic, which often involves operations that interact with the data layer (repositories),
// but it doesn't handle HTTP requests (which is the responsibility of controllers) or directly manage persistence (which is handled by repositories).
@Service
public class AccumulatedSaleService {
    
    // The @Autowired annotation automatically injects the SaleRepository dependency into the SaleService class.
    // Spring manages the creation of SaleRepository and its injection into SaleService, so we don't need to manually create it.
    @Autowired
    private AccumulatedSaleRepository AccumulatedSaleRepository;

    // Method to get all sales data from the database
    // This method uses the findAll() method of SaleRepository, which is implemented using Hibernate.
    // Hibernate takes care of converting between Java objects and database records.
    public List<AccumulatedSale> getAllSales() {
        return AccumulatedSaleRepository.findAll();  // Retrieve all Sale objects from the database
    }

    // Method to save a new sale entry to the database
    // This method calls the save() method of SaleRepository, which is handled by Hibernate.
    // Hibernate automatically inserts or updates the Sale object in the database.
    public AccumulatedSale saveSale(AccumulatedSale sale) {
        return AccumulatedSaleRepository.save(sale);  // Save the Sale object to the database
    }
}
