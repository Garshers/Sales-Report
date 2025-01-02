package com.salesreport.salesreport.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.salesreport.salesreport.model.SalesPerson;

@Repository
public interface SalesPersonRepository extends JpaRepository<SalesPerson, Long> {
}

