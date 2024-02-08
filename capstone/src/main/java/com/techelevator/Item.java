package com.techelevator;

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
    private Double price;

    public Item(String input) {
        String[] parts = input.split(",");
        this.slotLocation = parts[0];
        this.name = parts[1];
        this.price = Double.parseDouble(parts[2]);
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

    public Double getPrice() {
        return price;
    }
}
