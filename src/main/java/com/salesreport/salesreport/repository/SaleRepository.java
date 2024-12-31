package com.salesreport.salesreport.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.salesreport.salesreport.model.Sale;

// The @Repository annotation indicates that this class or interface is responsible for data access.
// It is a Spring component that enables easy interaction with the database. 
// In this case, Spring automatically creates an implementation of the repository at runtime.
@Repository
public interface SaleRepository extends JpaRepository<Sale, Long> {
    
    // JpaRepository provides default implementations for methods to manage data:
    // - findAll(): retrieves all records,
    // - findById(): finds a record by its identifier,
    // - save(): saves or updates a record in the database,
    // - deleteById(): deletes a record by its identifier.
    //
    // By extending JpaRepository, the SaleRepository interface does not need to provide its own implementations
    // for these methods, as Spring Data JPA automatically provides the required functionality.
}
