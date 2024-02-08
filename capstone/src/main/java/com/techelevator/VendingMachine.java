package com.techelevator;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.InputMismatchException;
import java.util.Map;
import java.util.Scanner;

public class VendingMachine {
    private Map<String, Slot> slots = new HashMap<>();
    private Account account = new Account();
    Scanner scanner = new Scanner(System.in);

    public VendingMachine() {
        String fileName = "vendingmachine.csv";
        try(Scanner fileScanner = new Scanner(new File(fileName))){
            while(fileScanner.hasNextLine()) {
                Item item = new Item(fileScanner.nextLine());
                Slot slot = new Slot(item);
                slots.put(item.getSlotLocation(), slot);
            }

        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
    public void mainMenu(){

        String inputChoice = "";
        do {
            System.out.println();
            System.out.println("(1) Display Vending Machine Items");
            System.out.println("(2) Purchase");
            System.out.println("(3) Exit");
            inputChoice = scanner.nextLine();
            if(inputChoice.equals("1")){
                displayItems();
            }else if(inputChoice.equals("2")){
                purchaseMenu();
            }
        }while(!inputChoice.equals("3"));

    }
    private void displayItems(){
        for (Map.Entry<String, Slot> element : slots.entrySet()) {
            System.out.println(element.getKey() + ": " + element.getValue());
        }
    }
    private void purchaseMenu(){
        String inputChoice;
        boolean keepGoing = true;
        do {
            System.out.println("Current Money Provided: $" + account.getBalance() );
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
        }while(keepGoing);

    }
    private void feedMoney(){
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

    private void selectProduct(){

    }
    private void finishTransaction(){

    }
}
