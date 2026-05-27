package com.pluralsight.model;

public class MenuAddition {
    // These fields are from our data about menu items.
    private String name;
    private MenuAdditionCategory category;
    private double[] pricePerSizeClass;
    private double[] extraPricePerSizeClass;
    private boolean premium;
    private boolean canExtra;
    double computePrice(Size size, boolean wantsExtra) {
        double total = pricePerSizeClass[size.getId()];
        if (wantsExtra && extraPricePerSizeClass != null) {
            total += extraPricePerSizeClass[size.getId()];
        }
        return total;
    }

    public MenuAddition(String name, MenuAdditionCategory category, double[] pricePerSizeClass, double[] extraPricePerSizeClass, boolean premium, boolean canExtra) {
        this.name = name;
        this.category = category;
        this.pricePerSizeClass = pricePerSizeClass;
        this.extraPricePerSizeClass = extraPricePerSizeClass;
        this.premium = premium;
        this.canExtra = canExtra;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public MenuAdditionCategory getCategory() {
        return category;
    }

    public void setCategory(MenuAdditionCategory category) {
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

    public boolean isCanExtra() {
        return canExtra;
    }

    public void setCanExtra(boolean canExtra) {
        this.canExtra = canExtra;
    }
}
