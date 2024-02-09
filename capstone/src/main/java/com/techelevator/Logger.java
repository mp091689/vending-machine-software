package com.techelevator;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Logger {
    private String fileName = "log.txt";
    private boolean prefixTimeStamp = true;

    public Logger() {

    }

    public Logger(String fileName) {
        this.fileName = fileName;
    }

    public void setPrefixTimeStamp(boolean prefixTimeStamp) {
        this.prefixTimeStamp = prefixTimeStamp;
    }

    public void info(String information) {
        String prefix = prefixTimeStamp ? LocalDateTime.now().format(DateTimeFormatter.ofPattern("MM/dd/yyyy HH:mm:ss a ")) : "";
        try (PrintWriter pw = new PrintWriter(new FileOutputStream(fileName, true))) {
            pw.println(prefix + information);
        } catch (FileNotFoundException e) {
            System.out.println("Could not create log.txt file");
        }
    }
}
