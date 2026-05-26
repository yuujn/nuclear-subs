package com.pluralsight.model;


import java.util.ArrayList;
import java.util.List;

public class Sandwich implements LineItem {
    public static final int SMALL = 0;
    public static final int MEDIUM = 1;
    public static final int LARGE = 2;

    private int size;
    private List<Addition> additions;
    private boolean toasted;
    private String name;

    public Sandwich() {
        this.additions = new ArrayList<>();
    }
    public Sandwich(int size, List<Addition> additions, boolean toasted, String name) {
        this.size = size;
        this.additions = additions;
        this.toasted = toasted;
        this.name = name;
    }

    @Override
    public double getPrice() {
        double basePrice = switch (size) {
            case SMALL -> 5.50;
            case MEDIUM -> 7.00;
            case LARGE -> 8.50;
            default -> throw new RuntimeException("Unknown sandwich size");
        };
        return basePrice + additions.stream()
                .mapToDouble(x -> x.computePrice(size))
                .sum();
    }

    @Override
    public String toString() {
        return "Sandwich{" +
                "size=" + size +
                ", additions=" + additions +
                ", toasted=" + toasted +
                ", name='" + name + '\'' +
                '}';
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public List<Addition> getAdditions() {
        return additions;
    }

    public void setAdditions(List<Addition> additions) {
        this.additions = additions;
    }

    public boolean isToasted() {
        return toasted;
    }

    public void setToasted(boolean toasted) {
        this.toasted = toasted;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
