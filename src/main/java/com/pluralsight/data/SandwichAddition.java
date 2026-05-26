package com.pluralsight.data;

class SandwichAddition {
    private int id;
    private int categoryId;
    private String name;

    private SandwichAddition() {}
    public SandwichAddition(int id, int categoryId, String name) {
        this.id = id;
        this.categoryId = categoryId;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public static SandwichAddition fromCSVRow(String[] fieldMap, String[] fields) {
        SandwichAddition addition = new SandwichAddition();
        for (int i = 0; i < fields.length; i++) {
            String field = fields[i];
            switch (fieldMap[i].toLowerCase()) {
                case "id" -> addition.setId(Integer.parseInt(field));
                case "category-id" -> addition.setCategoryId(Integer.parseInt(field));
                case "name" -> addition.setName(field);
            }
        }
        return addition;
    }
}
