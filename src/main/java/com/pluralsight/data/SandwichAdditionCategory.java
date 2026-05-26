package com.pluralsight.data;

import java.util.Arrays;

class SandwichAdditionCategory {
    private int id;
    private String name;
    private double[] pricesBySize;
    private double[] extraPricesBySize;
    private boolean canExtra;

    private SandwichAdditionCategory() {
    }

    public SandwichAdditionCategory(int id, String name, double[] pricesBySize, double[] extraPricesBySize) {
        this.id = id;
        this.name = name;
        this.pricesBySize = pricesBySize;
        this.extraPricesBySize = extraPricesBySize;
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

    public double[] getPricesBySize() {
        return pricesBySize;
    }

    public void setPricesBySize(double[] pricesBySize) {
        this.pricesBySize = pricesBySize;
    }

    public double[] getExtraPricesBySize() {
        return extraPricesBySize;
    }

    public void setExtraPricesBySize(double[] extraPricesBySize) {
        this.extraPricesBySize = extraPricesBySize;
    }

    public boolean isCanExtra() {
        return canExtra;
    }

    public void setCanExtra(boolean canExtra) {
        this.canExtra = canExtra;
    }

    public static SandwichAdditionCategory fromCSVRow(String[] fieldMap, String[] fields) {
        SandwichAdditionCategory category = new SandwichAdditionCategory();
        for (int i = 0; i < fields.length; i++) {
            String field = fields[i];
            switch (fieldMap[i].toLowerCase()) {
                case "id" -> category.setId(Integer.parseInt(field));
                case "name" -> category.setName(field);
                case "prices-by-size" -> category.setPricesBySize(
                        Arrays.stream(field.split(","))
                                .mapToDouble(Double::parseDouble)
                                .toArray());
                case "extra-prices-by-size" -> {
                    if (!field.equalsIgnoreCase("null")) {
                        category.setExtraPricesBySize(
                                Arrays.stream(field.split(","))
                                        .mapToDouble(Double::parseDouble)
                                        .toArray()
                        );
                    }
                }
                case "can-extra" -> category.setCanExtra(Boolean.parseBoolean(field));
            }
        }
        return category;
    }
}
