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

    @SuppressWarnings("unused")
    public void setName(String name) {
        this.name = name;
    }

    public MenuAdditionCategory getCategory() {
        return category;
    }

    @SuppressWarnings("unused")
    public void setCategory(MenuAdditionCategory category) {
        this.category = category;
    }

    @SuppressWarnings("unused")
    public double[] getPricePerSizeClass() {
        return pricePerSizeClass;
    }

    @SuppressWarnings("unused")
    public void setPricePerSizeClass(double[] pricePerSizeClass) {
        this.pricePerSizeClass = pricePerSizeClass;
    }

    @SuppressWarnings("unused")
    public double[] getExtraPricePerSizeClass() {
        return extraPricePerSizeClass;
    }

    @SuppressWarnings("unused")
    public void setExtraPricePerSizeClass(double[] extraPricePerSizeClass) {
        this.extraPricePerSizeClass = extraPricePerSizeClass;
    }

    @SuppressWarnings("unused")
    public boolean isPremium() {
        return premium;
    }

    @SuppressWarnings("unused")
    public void setPremium(boolean premium) {
        this.premium = premium;
    }

    public boolean isCanExtra() {
        return canExtra;
    }

    @SuppressWarnings("unused")
    public void setCanExtra(boolean canExtra) {
        this.canExtra = canExtra;
    }
}
