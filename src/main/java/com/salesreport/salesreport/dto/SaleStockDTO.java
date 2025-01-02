package com.salesreport.salesreport.dto;

import java.math.BigDecimal;

public class SaleStockDTO {
    private String productTypeName;
    private int quantitySold;
    private BigDecimal totalProfit;
    private long productId; 

    // Constructor
    public SaleStockDTO(String productTypeName, int quantitySold, BigDecimal totalProfit, long productId) {
        if (totalProfit == null) {
            throw new IllegalArgumentException("totalProfit cannot be null");
        }
        this.productTypeName = productTypeName;
        this.quantitySold = quantitySold;
        this.totalProfit = totalProfit;
        this.productId = productId;
    }

    public String getProductName() {
        return productTypeName;
    }

    public void setProductName(String productTypeName) {
        this.productTypeName = productTypeName;
    }

    public int getQuantitySold() {
        return quantitySold;
    }

    public void setQuantitySold(int quantitySold) {
        this.quantitySold = quantitySold;
    }

    public BigDecimal getTotalProfit() {
        return totalProfit;
    }

    public void setTotalProfit(BigDecimal totalProfit) {
        if (totalProfit == null) {
            throw new IllegalArgumentException("totalProfit cannot be null");
        }
        this.totalProfit = totalProfit;
    }

    public long getProductId() {
        return productId;
    }

    public void setProductId(long productId) {
        this.productId = productId;
    }

    // Method for debugging
    @Override
    public String toString() {
        return "SaleStockDTO{" +
                "productName='" + productTypeName + '\'' +
                ", quantitySold=" + quantitySold +
                ", totalProfit=" + totalProfit +
                ", productId=" + productId +
                '}';
    }
}
