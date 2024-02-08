package com.techelevator;

public class Slot {
    private int quantity = 5;
    private Item item;

    public Slot(Item item) {
        this.item = item;
    }

    public void dispense() {
        this.quantity--;
    }

    public int getQuantity() {
        return quantity;
    }

    public Item getItem() {
        return item;
    }
}
