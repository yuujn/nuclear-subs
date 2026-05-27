package com.pluralsight.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static com.pluralsight.ui.StringUtil.titleCase;

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

    public String generateReceipt() {
        StringBuilder buf = new StringBuilder();

        // TODO: It would be nice to make this output all aligned.
        buf.append(String.format("Total: $%.2f", calculateTotal()));
        buf.append("\n");

        for (LineItem item : getItems()) {
            buf.append(String.format("%s    .......    $%.2f", titleCase(item.getReceiptEntry()), item.getPrice()));
            if (item instanceof Sandwich sandwich) {
                Addition[] components = sandwich.getCategories().stream()
                        .flatMap(x -> x.getAdditions().stream())
                        .toArray(Addition[]::new);
                for (Addition component : components) {
                    buf.append("\n");
                    buf.append(String.format(
                            "   %s%s ...    $%.2f",
                            titleCase(component.getMenuAddition().getName()),
                            component.isWantsExtra() ? " (EX)":"",
                            component.computePrice(sandwich.getSize())
                    ));
                }
                buf.append("\n");
            }
        }

        return buf.toString();
    }
}
