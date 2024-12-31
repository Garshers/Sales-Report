package com.salesreport.salesreport.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

// The @Entity annotation indicates that this class is an entity class.
// An entity is a Java object that will be mapped to a database table.
// In this case, the Sale class represents a record in the "sale" table in the database.
@Entity
// The @Table annotation specifies the name of the table in the database that this entity will be mapped to.
// Here, it is specifying that the entity "Sale" will be mapped to the "sale" table.
@Table(name = "sale")
public class Sale {

    // The @Id annotation marks this field as the primary key for the entity.
    // This is the unique identifier for each record in the "sale" table.
    @Id
    // The @GeneratedValue annotation specifies how the primary key should be generated.
    // In this case, the strategy used is GenerationType.IDENTITY, which means the database will auto-generate the value for the primary key.
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // These fields represent the properties of the sale. Each will be mapped to a column in the "sale" table.
    private String productName;  // The name of the product in the sale.
    private int quantity;        // The quantity of the product sold.
    private double price;        // The price of the product in the sale.

    // Getters and setters for each field.

    // Getter for the id field.
    public Long getId() {
        return id;
    }

    // Setter for the id field.
    public void setId(Long id) {
        this.id = id;
    }

    // Getter for the productName field.
    public String getProductName() {
        return productName;
    }

    // Setter for the productName field.
    public void setProductName(String productName) {
        this.productName = productName;
    }

    // Getter for the quantity field.
    public int getQuantity() {
        return quantity;
    }

    // Setter for the quantity field.
    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    // Getter for the price field.
    public double getPrice() {
        return price;
    }

    // Setter for the price field.
    public void setPrice(double price) {
        this.price = price;
    }
}