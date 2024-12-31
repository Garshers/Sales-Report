package com.salesreport.salesreport.controller;

import java.util.List;

public class ChartData {
    private List<String> labels;
    private List<Double> values;

    // Constructor
    public ChartData(List<String> labels, List<Double> values) {
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

    public List<Double> getValues() {
        return values;
    }

    public void setValues(List<Double> values) {
        this.values = values;
    }
}