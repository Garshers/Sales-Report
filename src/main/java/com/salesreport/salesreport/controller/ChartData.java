package com.salesreport.salesreport.controller;

import java.util.List;

public class ChartData<T> {
    private List<String> labels;
    private List<T> values;
    // Changing data type to T, this enebles to use this class in more versitale manner

    // Constructor
    public ChartData(List<String> labels, List<T> values) {
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

    public List<T> getValues() {
        return values;
    }

    public void setValues(List<T> values) {
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