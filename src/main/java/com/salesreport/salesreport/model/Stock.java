package com.salesreport.salesreport.model;

import java.math.BigDecimal;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table (name = "stock")
public class Stock {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    @Column(name = "product_id")
    Long productId;

    @Column(name = "product_type")
    String productType;

    @Column(name = "product_name")
    String productName;

    @Column(name = "manufacturer_name")
    String manufacturerName;

    @Column(name = "manufacturer_id")
    String manufacturerId;

    BigDecimal price; // no need for @Column - same name

    public Long getProductId() {
        return productId;
    }

    public String getProductType() {
        return productType;
    }

    public String getProductName() {
        return productName;
    }

    public String getManufacturerName() {
        return manufacturerName;
    }

    public String getManufacturerId() {
        return manufacturerId;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public Long getTotalProfit() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public void setProductType(String productType) {
        this.productType = productType;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public void setManufacturerName(String manufacturerName) {
        this.manufacturerName = manufacturerName;
    }

    public void setManufacturerId(String manufacturerId) {
        this.manufacturerId = manufacturerId;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public void setTotalProfit(Long productId) {
        this.productId = productId;
    }
}
