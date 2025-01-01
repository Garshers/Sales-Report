package com.salesreport.salesreport.controller;

import java.math.BigDecimal;
import java.util.List;

public class ChartData {
    private List<String> labels;
    private List<BigDecimal> values;

    // Constructor
    public ChartData(List<String> labels, List<BigDecimal> values) {
        this.labels = labels;
        this.values = values;
    }

    // Getters and setters
    public List<String> getLabels() {
        return labels;
    }

    public void setLabels(List<String> labels) {
        this.labels = labels;
    }

    public List<BigDecimal> getValues() {
        return values;
    }

    public void setValues(List<BigDecimal> values) {
        this.values = values;
    }

    @Override
    public String toString() {
        return "ChartData{" +
                "labels=" + this.labels +
                ", values=" + this.values +
                '}';
    }
}