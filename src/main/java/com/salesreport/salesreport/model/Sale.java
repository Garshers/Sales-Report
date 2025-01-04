package com.salesreport.salesreport.model;

import java.math.BigDecimal;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table (name = "sales")
public class Sale {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "sale_id")
    private Long saleId; 
    
    @Column(name = "product_id")
    private Long productId;  

    @Column(name = "discount_id")
    private Long discountId;  
    
    @Column(name = "price")
    private BigDecimal price; 

    @Column(name = "store_type")
    private String storeType;  
    
    @Column(name = "sales_person_id")
    private Long salesPersonId;  
    

    public Long getSaleId() {
        return saleId;
    }

    public Long getProductId() {
        return productId;
    }

    public Long getDiscountId() {
        return discountId;
    }

    public void setDiscountId(Long discountId) {
        this.discountId = discountId;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public String getStoreType() {
        return storeType;
    }

    public void setStoreType(String storeType) {
        this.storeType = storeType;
    }

    public Long getSalesPersonId() {
        return salesPersonId;
    }

    public void setSalesPersonId(Long salesPersonId) {
        this.salesPersonId = salesPersonId;
    }

    @Override
    public String toString() {
        return "Sale{" +
                "saleId=" + saleId +
                ", productId=" + productId +
                ", discountId=" + discountId +
                ", price=" + price +
                ", storeType='" + storeType + '\'' +
                ", salesPersonId=" + salesPersonId +
                '}';
    }
 
}
