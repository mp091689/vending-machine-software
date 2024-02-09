package com.techelevator;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

class LoggerTest {

    @Test
    void testDefaultFileName() {
        String expected = "log.txt";
        Logger logger = new Logger();
        Assertions.assertEquals(expected, logger.getFileName());
    }

    @Test
    void testCustomFileName() {
        String expected = "customFileName.txt";
        Logger logger = new Logger(expected);
        Assertions.assertEquals(expected, logger.getFileName());
    }

    @Test
    void testDefaultPrefixTimeStamp() {
        Logger logger = new Logger();
        Assertions.assertTrue(logger.isPrefixTimeStamp());
    }

    @Test
    void testCustomPrefixTimeStamp() {
        Logger logger = new Logger();
        logger.setPrefixTimeStamp(false);
        Assertions.assertFalse(logger.isPrefixTimeStamp());
    }

    @Test
    void testLogFileCreated() {
        Logger logger = new Logger("test.txt");
        logger.info("test");
        File file = new File(logger.getFileName());

        Assertions.assertTrue(file.exists());

        file.delete();
    }

    @Test
    void testLogContentWithPrefix() {
        Logger logger = new Logger("test.txt");
        logger.info("test");
        boolean actual = false;
        File file = new File(logger.getFileName());
        try (Scanner fileScanner = new Scanner(file)) {
            while (fileScanner.hasNextLine()) {
                actual = fileScanner.nextLine().matches("^[0-9]{2}\\/[0-9]{2}\\/[0-9]{4} [0-9]{2}:[0-9]{2}:[0-9]{2} [A|P]M test$");
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }

        Assertions.assertTrue(actual);
        file.delete();
    }

    @Test
    void testLogContentWithOutPrefix() {
        Logger logger = new Logger("test.txt");
        logger.setPrefixTimeStamp(false);
        logger.info("test");
        boolean actual = false;
        File file = new File(logger.getFileName());
        try (Scanner fileScanner = new Scanner(file)) {
            while (fileScanner.hasNextLine()) {
                actual = fileScanner.nextLine().matches("^test$");
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }

        Assertions.assertTrue(actual);
        file.delete();
    }
}