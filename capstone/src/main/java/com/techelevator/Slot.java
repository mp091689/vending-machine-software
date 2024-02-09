package com.techelevator;

import java.math.BigDecimal;

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

    public BigDecimal getPrice() {
        return item.getPrice();
    }

    public String getName() {
        return item.getName();
    }

    public String getNType() {
        return item.getType();
    }

}
