package com.salesreport.salesreport.dto;

import java.math.BigDecimal;

public class SalePersonWithSaleDTO {

    // Variables from 'sales_persons'
    private Long salesPersonId;
    private String salesPersonNameSurname;

    // Variables from 'sales'
    private Long productId;
    private String productTypeName;
    private BigDecimal price;

    // Constructor
    public SalePersonWithSaleDTO(Long salesPersonId, String salesPersonNameSurname,
                                Long productId, String productTypeName, BigDecimal price) {
        this.salesPersonId = salesPersonId;
        this.salesPersonNameSurname = salesPersonNameSurname;
        this.productId = productId;
        this.productTypeName = productTypeName;
        this.price = price;
    }

    public Long getSalesPersonId() {
        return salesPersonId;
    }

    public void setSalesPersonId(Long salesPersonId) {
        this.salesPersonId = salesPersonId;
    }

    public String getSalesPersonNameSurname() {
        return salesPersonNameSurname;
    }

    public void setSalesPersonNameSurname(String salesPersonNameSurname) {
        this.salesPersonNameSurname = salesPersonNameSurname;
    }

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public String getproductTypeName() {
        return productTypeName;
    }

    public void setproductTypeName(String productTypeName) {
        this.productTypeName = productTypeName;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }
}
