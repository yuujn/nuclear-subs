package com.pluralsight.data;

import com.pluralsight.model.Sandwich;

public class SignatureSandwichComponent {
    private int id;
    private int signatureSandwichId;
    private int sandwichAdditionId;
    private boolean extra;

    private SignatureSandwichComponent() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getSignatureSandwichId() {
        return signatureSandwichId;
    }

    public void setSignatureSandwichId(int signatureSandwichId) {
        this.signatureSandwichId = signatureSandwichId;
    }

    public int getSandwichAdditionId() {
        return sandwichAdditionId;
    }

    public void setSandwichAdditionId(int sandwichAdditionId) {
        this.sandwichAdditionId = sandwichAdditionId;
    }

    public boolean isExtra() {
        return extra;
    }

    public void setExtra(boolean extra) {
        this.extra = extra;
    }

    public static SignatureSandwichComponent fromCSVRow(String[] fieldMap, String[] fields) {
        SignatureSandwichComponent component = new SignatureSandwichComponent();
        for (int i = 0; i < fields.length; i++) {
            String field = fields[i];
            switch (fieldMap[i].toLowerCase()) {
                case "id" -> component.setId(Integer.parseInt(field));
                case "signature-id" -> component.setSignatureSandwichId(Integer.parseInt(field));
                case "addition-id" -> component.setSandwichAdditionId(Integer.parseInt(field));
                case "extra" -> component.setExtra(Boolean.parseBoolean(field));
            }
        }
        return component;
    }
}
