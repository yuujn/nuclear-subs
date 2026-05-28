package com.pluralsight.model;

import static com.pluralsight.ui.StringUtil.titleCase;

public class Drink implements LineItem {
    private String size;
    private String flavor;

    public Drink(String size, String flavor) {
        this.size = size;
        this.flavor = flavor;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    @SuppressWarnings("unused")
    public String getFlavor() {
        return flavor;
    }

    @SuppressWarnings("unused")
    public void setFlavor(String flavor) {
        this.flavor = flavor;
    }

    @Override
    public String getName() {
        return flavor;
    }

    @Override
    public String getReceiptEntry() {
        return String.format("%s (%s)", getName(), getSize());
    }

    @Override
    public double getPrice() {
        return switch (size.toLowerCase()) {
            case "small" -> 2.00;
            case "medium" -> 2.50;
            case "large" -> 3.00;
            default -> throw new IllegalStateException("Unexpected value: " + size.toLowerCase());
        };
    }

    @Override
    public String generateOneLiner() {
        return titleCase(getReceiptEntry());
    }
}
