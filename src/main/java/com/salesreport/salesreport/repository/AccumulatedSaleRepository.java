package com.salesreport.salesreport.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.salesreport.salesreport.model.AccumulatedSale;

// The @Repository annotation marks this interface as a Spring Data repository, 
// responsible for data access. It enables easy interaction with the database 
// through methods provided by Spring Data JPA.
@Repository
public interface AccumulatedSaleRepository extends JpaRepository<AccumulatedSale, Long> {
    
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
