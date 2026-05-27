package com.pluralsight.model;


import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class Sandwich implements LineItem {
    private Size size;
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

    public Size getSize() {
        return size;
    }

    public void setSize(Size size) {
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
        if (name != null) {
            return name;
        } else {
            return "Sandwich";
        }
    }

    public void setName(String name) {
        this.name = name;
    }

    public void addComponent(Addition addition) {
        MenuAdditionCategory target = addition.getMenuAddition().getCategory();
        Optional<AdditionCategory> category = categories.stream()
                .filter(x -> x.getMenuAdditionCategory() == target)
                .findFirst();

        if (category.isPresent()) {
            category.get().getAdditions().add(addition);
        } else {
            AdditionCategory newCategory = new AdditionCategory(target);
            newCategory.getAdditions().add(addition);
            categories.add(newCategory);
        }
    }
}
