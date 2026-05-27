package com.pluralsight.data;

import com.pluralsight.model.Sandwich;
import com.pluralsight.model.Size;

import java.util.ArrayList;
import java.util.List;

public class SignatureSandwich {
    private int id;
    private int sizeId;
    private Sandwich sandwich;

    private SignatureSandwich() {
        this.sandwich = new Sandwich();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void initSize(List<Size> sizes) {
        sizes.forEach(x -> {
            if (x.getId() == this.sizeId) {
                this.sandwich.setSize(x);
            }
        });
    }

    public String getName() {
        return sandwich.getName();
    }

    public void setName(String name) {
        sandwich.setName(name);
    }

    public int getSizeId() {
        return sizeId;
    }

    public void setSizeId(int sizeId) {
        this.sizeId = sizeId;
    }

    public boolean isToasted() {
        return sandwich.isToasted();
    }

    public void setToasted(boolean toasted) {
        sandwich.setToasted(toasted);
    }

    Sandwich getInnerSandwich() {
        return sandwich;
    }

    public Sandwich makeSandwich() {
        Sandwich newSandwich = new Sandwich();
        newSandwich.setSize(sandwich.getSize());
        newSandwich.setCategories(new ArrayList<>(sandwich.getCategories()));
        newSandwich.setToasted(sandwich.isToasted());
        newSandwich.setName(getName());
        return newSandwich;
    }

    public static SignatureSandwich fromCSVRow(String[] fieldMap, String[] fields) {
        SignatureSandwich sandwich = new SignatureSandwich();
        for (int i = 0; i < fields.length; i++) {
            String field = fields[i];
            switch (fieldMap[i].toLowerCase()) {
                case "id" -> sandwich.setId(Integer.parseInt(field));
                case "name" -> sandwich.setName(field);
                case "size" -> sandwich.setSizeId(Integer.parseInt(field));
                case "toasted" -> sandwich.setToasted(Boolean.parseBoolean(field));
            }
        }
        return sandwich;
    }
}
