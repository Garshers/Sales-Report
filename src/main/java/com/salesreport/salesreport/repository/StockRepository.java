package com.salesreport.salesreport.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.salesreport.salesreport.model.Stock;

@Repository
public interface StockRepository extends JpaRepository<Stock, Long> {
    
    // JpaRepository provides default implementations for common data management methods, 
    // such as:
    // - findAll(): Retrieves all records from the database.
    // - findById(): Finds a record by its identifier.
    // - save(): Saves or updates a record in the database.
    // - deleteById(): Deletes a record based on its identifier.
    //
    // By extending JpaRepository, SaleRepository inherits these methods 
    // and does not need to provide custom implementations for them. 
    // Spring Data JPA automatically provides the required functionality.
}