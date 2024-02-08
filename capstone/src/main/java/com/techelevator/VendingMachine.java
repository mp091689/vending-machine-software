package com.techelevator;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class VendingMachine {
    private Map<String, Slot> slots = new HashMap<>();
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
        String inputChoice = "";
        boolean keepGoing = true;
        do {
            System.out.println("Current Money Provided: $"  );
            System.out.println();
            System.out.println("(1) Feed Money");
            System.out.println("(2) Select Product");
            System.out.println("(3) Finish Transaction");
            inputChoice = scanner.nextLine();
            if(inputChoice.equals("1")){
                feedMoney();
            }else if(inputChoice.equals("2")){
                selectProduct();
            }else if(inputChoice.equals("3")){
                finishTransaction();
                keepGoing = false;
            }else{
                System.out.println("Please choose one of the options above");
            }
        }while(keepGoing);

    }
    private double feedMoney(){
        return 0.0;
    }
    private void selectProduct(){

    }
    private void finishTransaction(){

    }
}
