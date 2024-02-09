package com.techelevator;

import java.io.File;
import java.io.FileNotFoundException;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Map;
import java.util.Scanner;
import java.util.SortedMap;
import java.util.TreeMap;

public class VendingMachine {
    private final String ANSI_RED = "\u001b[31m";
    private final String ANSI_RESET = "\u001b[0m";
    private final String ANSI_UNDERSCORE = "\u001b[4m";
    Scanner scanner = new Scanner(System.in);
    private SortedMap<String, Slot> slots = new TreeMap<>();
    private Account account = new Account();
    private Logger logger = new Logger();

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
            System.out.print("Please enter your choice: ");
            inputChoice = scanner.nextLine();
            switch (inputChoice) {
                case "1":
                    displayItems();
                    break;
                case "2":
                    purchaseMenu();
                    break;
                case "4":
                    salesReport();
                    break;
            }
        } while (!inputChoice.equals("3"));
    }

    private void displayItems() {
        System.out.println();
        System.out.printf("%s%-5s %-20s %s\033[0m%n", ANSI_UNDERSCORE, "Slot", "Item Name", "Price");
        for (Map.Entry<String, Slot> element : slots.entrySet()) {
            String location = element.getKey();
            String name = element.getValue().getName();
            String pricePlace = "$" + element.getValue().getPrice().toString();
            if (element.getValue().getQuantity() <= 0) {
                pricePlace = "SOLD OUT";
            }
            System.out.printf("%-5s %-20s %s%n", location, name, pricePlace);
        }
        System.out.println();
    }

    private void purchaseMenu() {
        String inputChoice;
        boolean keepGoing = true;
        do {
            System.out.println();
            System.out.println("Current Money Provided: $" + account.getBalance());
            System.out.println();
            System.out.println("(1) Feed Money");
            System.out.println("(2) Select Product");
            System.out.println("(3) Finish Transaction");
            System.out.print("Please enter your choice: ");
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

    private void salesReport() {
        String fileNameDateTimeStamp = LocalDateTime.now().format(DateTimeFormatter.ofPattern("MM-dd-yyyy-HH:mm:ss"));
        Logger salesLogger = new Logger("report_" + fileNameDateTimeStamp + ".csv");
        salesLogger.setPrefixTimeStamp(false);
        BigDecimal total = new BigDecimal(0);
        total.setScale(2);
        for (Map.Entry<String, Slot> slot : slots.entrySet()) {
            int soldItems = 5 - slot.getValue().getQuantity();
            total = total.add(slot.getValue().getPrice().multiply(new BigDecimal(soldItems)));
            salesLogger.info(slot.getValue().getName() + "," + soldItems);
        }
        salesLogger.info("");
        salesLogger.info(String.format("**TOTAL SALES** $%s", total));
    }

    private void feedMoney() {
        System.out.print("Enter amount of deposit in dollars: ");
        String input = scanner.nextLine();
        try {
            int deposit = Integer.parseInt(input);

            if (!account.isValidBill(deposit)) {
                System.out.printf("%sInvalid bill %d%s.%nValid bills: 1, 5, 10, 20%n", ANSI_RED, deposit, ANSI_RESET);
            } else if (account.deposit(deposit)) {
                logger.info("FEED MONEY: $" + deposit + " $" + account.getBalance());
            } else {
                System.out.printf("%sInvalid amount:%s %d%n", ANSI_RED, ANSI_RESET, deposit);
            }
        } catch (Exception e) {
            System.out.printf("%sInvalid input:%s %s%n", ANSI_RED, ANSI_RESET, input);
        }
    }

    private void selectProduct() {
        displayItems();
        System.out.println();
        System.out.print("Please enter your selection: ");
        String input = scanner.nextLine().toUpperCase();

        if (slots.containsKey(input)) {
            int itemQuantity = slots.get(input).getQuantity();
            if (itemQuantity > 0) {
                BigDecimal itemPrice = slots.get(input).getPrice();
                if (account.withdraw(itemPrice)) {
                    slots.get(input).dispense();
                    logger.info(slots.get(input).getName() + ": $" + itemPrice + " $" + account.getBalance());
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
        logger.info("GIVE CHANGE: $" + account.returnCoins() + " $" + account.getBalance());
    }
}
