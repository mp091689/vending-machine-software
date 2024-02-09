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
    private String sound;

    public Item(String input) throws Exception{
        String[] parts = input.split(",");
        slotLocation = parts[0];
        name = parts[1];
        price = new BigDecimal(parts[2]);
        price.setScale(2);
        type = parts[3];
        switch (parts[3]) {
            case "Duck":
                sound = "Quack, Quack, Splash!";
                break;
            case "Penguin":
                sound = "Squawk, Squawk, Whee!";
                break;
            case "Cat":
                sound = "Meow, Meow, Meow!";
                break;
            case "Pony":
                sound = "Neigh, Neigh, Yay!";
                break;
            default:
                throw new Exception("Invalid animal");
        }
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

    public String getSound() {
        return sound;
    }
}
