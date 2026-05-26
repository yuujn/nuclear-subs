package com.pluralsight.model;

public class MenuAddition {
    // These fields are from our data about menu items.
    private String name;
    private String category;
    private double[] pricePerSizeClass;
    private double[] extraPricePerSizeClass;
    private boolean premium;
    double computePrice(int size, boolean wantsExtra) {
        double total = pricePerSizeClass[size];
        if (wantsExtra) {
            total += extraPricePerSizeClass[size];
        }
        return total;
    }

    public MenuAddition() {}
    public MenuAddition(String name, String category, double[] pricePerSizeClass, double[] extraPricePerSizeClass, boolean premium) {
        this.name = name;
        this.category = category;
        this.pricePerSizeClass = pricePerSizeClass;
        this.extraPricePerSizeClass = extraPricePerSizeClass;
        this.premium = premium;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public double[] getPricePerSizeClass() {
        return pricePerSizeClass;
    }

    public void setPricePerSizeClass(double[] pricePerSizeClass) {
        this.pricePerSizeClass = pricePerSizeClass;
    }

    public double[] getExtraPricePerSizeClass() {
        return extraPricePerSizeClass;
    }

    public void setExtraPricePerSizeClass(double[] extraPricePerSizeClass) {
        this.extraPricePerSizeClass = extraPricePerSizeClass;
    }

    public boolean isPremium() {
        return premium;
    }

    public void setPremium(boolean premium) {
        this.premium = premium;
    }
}
