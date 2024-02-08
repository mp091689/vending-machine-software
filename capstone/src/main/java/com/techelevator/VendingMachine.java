package com.techelevator;

import java.io.File;
import java.io.FileNotFoundException;
import java.math.BigDecimal;
import java.util.*;

public class VendingMachine {
    Scanner scanner = new Scanner(System.in);
    private SortedMap<String, Slot> slots = new TreeMap<>();
    private Account account = new Account();

    public VendingMachine() {
        String fileName = "vendingmachine.csv";
        try (Scanner fileScanner = new Scanner(new File(fileName))) {
            while (fileScanner.hasNextLine()) {
                Item item = new Item(fileScanner.nextLine());
                Slot slot = new Slot(item);
                slots.put(item.getSlotLocation(), slot);
            }

        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public void mainMenu() {

        String inputChoice = "";
        do {
            System.out.println();
            System.out.println("(1) Display Vending Machine Items");
            System.out.println("(2) Purchase");
            System.out.println("(3) Exit");
            inputChoice = scanner.nextLine();
            if (inputChoice.equals("1")) {
                displayItems();
            } else if (inputChoice.equals("2")) {
                purchaseMenu();
            }
        } while (!inputChoice.equals("3"));

    }

    private void displayItems() {
        System.out.printf("\033[4m%-5s %-20s %s\033[0m%n", "Slot", "Item Name", "Price");
        for (Map.Entry<String, Slot> element : slots.entrySet()) {
            String location = element.getKey();
            String name = element.getValue().getItem().getName();
            String pricePlace = "$" + element.getValue().getItem().getPrice().toString();
            if (element.getValue().getQuantity() <= 0) {
                pricePlace = "SOLD OUT";
            }
            System.out.printf("\u001b[32m%-5s\u001b[0m %-20s %s%n", location, name, pricePlace);
        }
        System.out.println();
    }

    private void purchaseMenu() {
        String inputChoice;
        boolean keepGoing = true;
        do {
            System.out.println("Current Money Provided: $" + account.getBalance());
            System.out.println();
            System.out.println("(1) Feed Money");
            System.out.println("(2) Select Product");
            System.out.println("(3) Finish Transaction");
            inputChoice = scanner.nextLine();
            switch (inputChoice) {
                case "1":
                    feedMoney();
                    break;
                case "2":
                    selectProduct();
                    break;
                case "3":
                    finishTransaction();
                    keepGoing = false;
                    break;
                default:
                    System.out.println("Please choose one of the options above");
                    break;
            }
        } while (keepGoing);

    }

    private void feedMoney() {
        System.out.print("Enter amount of deposit in dollars: ");
        String input = scanner.nextLine();
        try {
            int deposit = Integer.parseInt(input);
            if (!account.deposit(deposit)) {
                System.out.printf("\u001b[31mInvalid amount:\u001b[0m %d%n", deposit);
            }
        } catch (Exception e) {
            System.out.printf("\u001b[31mInvalid input:\u001b[0m %s%n", input);
        }
    }

    private void selectProduct() {
        displayItems();
        System.out.println();
        System.out.print("Please enter your selection: ");
        String input = scanner.nextLine().toUpperCase();

        if (slots.containsKey(input)) {
            int itemQuantity = slots.get(input).getQuantity();
            //System.out.println(itemQuantity);
            if (itemQuantity > 0) {
                BigDecimal itemPrice = slots.get(input).getItem().getPrice();
                //System.out.println(itemPrice);
                if (account.withdraw(itemPrice)) {
                    slots.get(input).dispense();
                    System.out.println("Successful purchase.");
                } else {
                    System.out.println("Insufficient funds.");
                }
            } else {
                System.out.println("Item is out of stock.");
            }

        } else {
            System.out.println("Item not found.");
        }
    }

    private void finishTransaction() {

    }
}
