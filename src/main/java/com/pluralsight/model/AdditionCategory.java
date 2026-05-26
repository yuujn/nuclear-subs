package com.pluralsight.model;

import java.util.List;

public class AdditionCategory {
    private int id;
    private String name;
    private List<MenuAddition> additions;
    // Unlike canExtra, which makes sense as a per-component attribute,
    // since someone can ask for extra ham when asking for ham,
    // canMany makes no sense as a per-component attribute,
    // it is explicitly about the category of thing we're talking about.
    private boolean canMany;

    public AdditionCategory(int id, String name, List<MenuAddition> additions, boolean canMany) {
        this.id = id;
        this.name = name;
        this.additions = additions;
        this.canMany = canMany;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public List<MenuAddition> getAdditions() {
        return additions;
    }

    public boolean isCanMany() {
        return canMany;
    }
}
