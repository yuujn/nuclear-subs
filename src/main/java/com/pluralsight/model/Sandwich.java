package com.pluralsight.model;


import java.util.ArrayList;
import java.util.List;

public class Sandwich implements LineItem {
    private int size;
    private List<AdditionCategory> categories;
    private boolean toasted;
    private String name;

    public Sandwich() {
        this.categories = new ArrayList<>();
    }

    @Override
    public double getPrice() {
        return categories.stream()
                .flatMap(x -> x.getAdditions().stream())
                .mapToDouble(x -> x.computePrice(size))
                .sum();
    }

    @Override
    public String toString() {
        return "Sandwich{" +
                "size=" + size +
                ", categories=" + categories +
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

    public List<AdditionCategory> getCategories() {
        return categories;
    }

    public void setCategories(List<AdditionCategory> categories) {
        this.categories = categories;
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
