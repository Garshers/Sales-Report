package com.salesreport.salesreport.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.salesreport.salesreport.model.Sale;

@Repository
public interface SaleRepository extends JpaRepository<Sale, Long> {

}
