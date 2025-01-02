package com.salesreport.salesreport.model;

import java.math.BigDecimal;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

// The @Entity annotation indicates that this class is an entity class,
// which means it will be mapped to a database table. In this case, the Sale
// class represents a record in the "accumulated_sale" table.
@Entity

// The @Table annotation specifies the name of the table in the database 
// to which this entity will be mapped. Here, it is specifying that 
// the entity "Sale" corresponds to the "accumulated_sale" table.
@Table(name = "accumulated_sale")
public class AccumulatedSale {

    // The @Id annotation designates this field as the primary key for the entity.
    // This primary key uniquely identifies each record in the "accumulated_sale" table.
    @Id

    // The @GeneratedValue annotation defines how the primary key should be generated.
    // In this case, GenerationType.IDENTITY is used, meaning the database 
    // will automatically generate the value for the primary key.
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    @Column(name = "product_id")
    private Long productId;

    @Column(name = "total_quantity_sold")
    private int totalQuantitySold;


    @Column(name = "total_profit")
    private BigDecimal totalProfit; // BigDecimal is more accurate than floating-point types


    public Long getProductId() {
        return productId;
    }

    public int getTotalQuantitySold() {
        return totalQuantitySold;
    }

    public BigDecimal getTotalProfit() {
        return totalProfit;
    }

}
