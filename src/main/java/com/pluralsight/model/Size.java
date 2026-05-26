package com.pluralsight.model;

public class Size {
    private int id;
    private String name;
    private String measurement;

    public Size(int id, String name, String measurement) {
        this.id = id;
        this.name = name;
        this.measurement = measurement;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMeasurement() {
        return measurement;
    }

    public void setMeasurement(String measurement) {
        this.measurement = measurement;
    }
}
