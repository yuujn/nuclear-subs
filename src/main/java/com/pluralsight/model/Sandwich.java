package com.pluralsight.model;


import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

import static com.pluralsight.ui.StringUtil.titleCase;

public class Sandwich implements LineItem {
    private Size size;
    private List<AdditionCategory> categories;
    private boolean toasted;
    private String name;

    public Sandwich() {
        this.categories = new ArrayList<>();
    }

    @Override
    public String getReceiptEntry() {
        return String.format("%s (%s)", getName(), getSize().getMeasurement());
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

    @SuppressWarnings("unused")
    public void setCategories(List<AdditionCategory> categories) {
        this.categories = categories;
    }

    @SuppressWarnings("unused")
    public boolean isToasted() {
        return toasted;
    }

    public void setToasted(boolean toasted) {
        this.toasted = toasted;
    }

    public String getName() {
        return Objects.requireNonNullElse(name, "Sandwich");
    }

    @SuppressWarnings("unused")
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

    public String generateSummary() {
        StringBuilder buf = new StringBuilder();

        buf.append(getName());
        buf.append(String.format(" (%s)", size.getMeasurement()));
        buf.append("\n");
        buf.append("-".repeat(getName().length() + 2));
        for (AdditionCategory category : getCategories()) {
            buf.append("\n");
            buf.append(titleCase(category.getMenuAdditionCategory().getName()));
            for (Addition addition : category.getAdditions()) {
                buf.append("\n");
                buf.append(" - ");
                buf.append(titleCase(addition.getMenuAddition().getName()));
                if (addition.isWantsExtra()) {
                    buf.append(" (EX)");
                }
                buf.append(" ... ");
                buf.append(String.format("$%.2f", addition.computePrice(size)));
            }
        }

        return buf.toString();
    }
}
