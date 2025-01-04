package com.salesreport.salesreport.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "sales_persons")
public class SalesPerson {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "sales_person_id")
    private Long salesPersonId;

    @Column(name = "sales_person_name")
    private String salesPersonName;

    @Column(name = "sales_person_surname")
    private String salesPersonSurname;

    public Long getSalesPersonId() {
        return salesPersonId;
    }

    public String getName() {
        return salesPersonName;
    }

    public void setName(String salesPersonName) {
        this.salesPersonName = salesPersonName;
    }

    public String getSurname() {
        return salesPersonSurname;
    }

    public void setSurname(String salesPersonSurname) {
        this.salesPersonSurname = salesPersonSurname;
    }

    @Override
    public String toString() {
        return "SalesPersons{" +
                "salesPersonId=" + salesPersonId +
                ", salesPersonName='" + salesPersonName + '\'' +
                ", salesPersonSurname='" + salesPersonSurname + '\'' +
                '}';
    }
}