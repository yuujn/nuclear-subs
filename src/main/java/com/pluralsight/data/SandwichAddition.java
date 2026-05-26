package com.pluralsight.data;

class SandwichAddition {
    private int id;
    private int categoryid;
    private String name;

    private SandwichAddition() {}
    public SandwichAddition(int id, int categoryid, String name) {
        this.id = id;
        this.categoryid = categoryid;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCategoryid() {
        return categoryid;
    }

    public void setCategoryid(int categoryid) {
        this.categoryid = categoryid;
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
                case "category-id" -> addition.setCategoryid(Integer.parseInt(field));
                case "name" -> addition.setName(field);
            }
        }
        return addition;
    }
}
