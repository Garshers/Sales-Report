package com.salesreport.salesreport.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.salesreport.salesreport.model.SalesPerson;
import com.salesreport.salesreport.repository.SalesPersonRepository;

@Service
public class SalesPersonService {
    @Autowired
    private SalesPersonRepository salesPersonRepository;

    // Get all sales persons
    public List<SalesPerson> getAllSalesPersons() {
        return salesPersonRepository.findAll();
    }

    public SalesPerson salesPerson(SalesPerson salesPerson) {
        return salesPersonRepository.save(salesPerson);  // Save the Stock object to the database
    }
}
