package com.techelevator;

import java.math.BigDecimal;

public class Item {
    @Override
    public String toString() {
        return "Item{" +
                "name='" + name + '\'' +
                ", type='" + type + '\'' +
                ", slotLocation='" + slotLocation + '\'' +
                ", price=" + price +
                '}';
    }

    private String name;
    private String type;
    private String slotLocation;
    private BigDecimal price;

    public Item(String input) {
        String[] parts = input.split(",");
        this.slotLocation = parts[0];
        this.name = parts[1];
        this.price = new BigDecimal(parts[2]);
        this.type = parts[3];
    }

    public String getName() {
        return name;
    }

    public String getType() {
        return type;
    }

    public String getSlotLocation() {
        return slotLocation;
    }

    public BigDecimal getPrice() {
        return price;
    }
}
