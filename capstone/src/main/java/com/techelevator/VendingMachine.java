package com.techelevator;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class VendingMachine {
    private Map<String, Slot> slots = new HashMap<>();

    public VendingMachine() {
        String fileName = "vendingmachine.csv";

        try(Scanner fileScanner = new Scanner(new File(fileName))){
            while(fileScanner.hasNextLine()) {
                Item item = new Item(fileScanner.nextLine());
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
}
