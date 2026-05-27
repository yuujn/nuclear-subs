package com.pluralsight.model;

public class Chips implements LineItem {
    private String type;

    public Chips(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public String getName() {
        return type;
    }

    @Override
    public double getPrice() {
        return 1.50;
    }
}
