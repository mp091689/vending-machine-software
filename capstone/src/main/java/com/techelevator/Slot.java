package com.techelevator;

public class Slot {
    @Override
    public String toString() {
        return "Slot{" +
                "quantity=" + quantity +
                ", item=" + item +
                '}';
    }

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
