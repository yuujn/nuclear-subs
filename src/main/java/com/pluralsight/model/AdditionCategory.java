package com.pluralsight.model;

import java.util.ArrayList;
import java.util.List;

public class AdditionCategory {
    private MenuAdditionCategory menuAdditionCategory;
    private List<Addition> additions;

    public AdditionCategory(MenuAdditionCategory menuAdditionCategory) {
        this.menuAdditionCategory = menuAdditionCategory;
        this.additions = new ArrayList<>();
    }

    public MenuAdditionCategory getMenuAdditionCategory() {
        return menuAdditionCategory;
    }

    public List<Addition> getAdditions() {
        return additions;
    }
}
