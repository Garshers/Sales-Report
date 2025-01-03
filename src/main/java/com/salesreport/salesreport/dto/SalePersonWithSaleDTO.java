package com.salesreport.salesreport.dto;

import java.math.BigDecimal;

public class SalePersonWithSaleDTO {

    // Variables from 'sales_persons'
    private Long salesPersonId;
    private String salesPersonNameSurname;

    // Variables from 'sales'
    private Long saleId;
    private Long productId;
    private BigDecimal price;

    // Variables from 'stock'
    private String productTypeName;

    // Constructor
    public SalePersonWithSaleDTO(Long saleId, Long salesPersonId, String salesPersonNameSurname,
                                Long productId, BigDecimal price, String productTypeName) {
        this.saleId = saleId;
        this.salesPersonId = salesPersonId;
        this.salesPersonNameSurname = salesPersonNameSurname;
        this.productId = productId;
        this.price = price;
        this.productTypeName = productTypeName;
    }

    public Long getSaleId() {
        return saleId;
    }

    public void setSaleId(Long saleId) {
        this.saleId = saleId;
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

    @Override
    public String toString() {
        return "SalePersonWithSaleDTO{" +
            "saleId=" + saleId +
            ", salesPersonId=" + salesPersonId +
            ", salesPersonNameSurname='" + salesPersonNameSurname + '\'' +
            ", productId=" + productId +
            ", price=" + price +
            ", productTypeName='" + productTypeName + '\'' +
            '}';
    }
}
