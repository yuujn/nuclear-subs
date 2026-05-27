package com.pluralsight.model;

public class Addition {
    private MenuAddition menuAddition;
    // These fields are customer input, for the particular order.
    private boolean wantsExtra;
    public double computePrice(int size) {
        return menuAddition.computePrice(size, wantsExtra);
    }

    public Addition(MenuAddition menuAddition, boolean wantsExtra) {
        this.menuAddition = menuAddition;
        this.wantsExtra = wantsExtra;
    }

    public MenuAddition getMenuAddition() {
        return menuAddition;
    }

    public void setMenuAddition(MenuAddition menuAddition) {
        this.menuAddition = menuAddition;
    }

    public boolean isWantsExtra() {
        return wantsExtra;
    }

    public void setWantsExtra(boolean wantsExtra) {
        this.wantsExtra = wantsExtra;
    }
}
