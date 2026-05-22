package com.pluralsight.model;

import java.util.ArrayList;
import java.util.List;

public class Order {
    private List<LineItem> items;

    public Order() {
        this.items = new ArrayList<>();
    }

    public List<LineItem> getItems() {
        return items;
    }

    public double calculateTotal() {
        return items.stream().mapToDouble(LineItem::getPrice).sum();
    }

    public void addItem(LineItem item) {
        items.add(item);
    }
}
