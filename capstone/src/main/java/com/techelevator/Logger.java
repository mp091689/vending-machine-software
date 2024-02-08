package com.techelevator;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.util.Scanner;

public class Logger {
    public void info(String information){
        try(PrintWriter pw = new PrintWriter(new FileOutputStream("log.txt", true))) {
            pw.println(information);
        } catch (FileNotFoundException e) {
            System.out.println("Could not create log.txt file");
        }
    }
}
