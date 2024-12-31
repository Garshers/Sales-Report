package com.salesreport.salesreport.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.salesreport.salesreport.model.Sale;
import com.salesreport.salesreport.repository.SaleRepository;

// The @Service annotation indicates that this class is a service component in the Spring application.
// It is used to define business logic and operations that interact with the data layer (repositories),
// but is not responsible for handling HTTP requests (controllers) or directly managing the persistence logic (repositories).
@Service
public class SaleService {
    
    // The @Autowired annotation automatically injects the dependency (SaleRepository) into the SaleService class.
    // This means that Spring will automatically provide an instance of SaleRepository when it creates the SaleService object.
    // With this approach, we don't need to manually create or pass the SaleRepository object. Spring manages it for us.
    @Autowired
    private SaleRepository saleRepository;

    // Method to get all sales data from the database
    // This method calls the findAll() method of the SaleRepository, which is implemented using Hibernate
    // Hibernate manages the conversion between Java objects and database records.
    public List<Sale> getAllSales() {
        return saleRepository.findAll();  // This retrieves all the Sale objects from the database
    }

    // Method to save a new sale entry to the database
    // This method calls the save() method of the SaleRepository, which is also handled by Hibernate
    // Hibernate will automatically insert the new Sale object into the corresponding table in the database.
    public Sale saveSale(Sale sale) {
        return saleRepository.save(sale);  // This saves the Sale object to the database
    }
}