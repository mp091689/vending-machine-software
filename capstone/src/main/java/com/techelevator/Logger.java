package com.techelevator;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Logger {
    public void info(String information) {
        try(PrintWriter pw = new PrintWriter(new FileOutputStream("log.txt", true))) {
            pw.println(LocalDateTime.now().format(DateTimeFormatter.ofPattern("MM/dd/yyyy HH:mm:ss a ")) + information);
        } catch (FileNotFoundException e) {
            System.out.println("Could not create log.txt file");
        }
    }
}
