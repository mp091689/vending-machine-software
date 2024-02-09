package com.techelevator;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class ItemTest {

    @Test
    void makeSoundCatMew() {
        try {
            //arrange
            Item i = new Item("C1,Black Cat,2.25,Cat");

            //act
            String actual = i.getSound();
            String expected = "Meow, Meow, Meow!";

            //assert
            Assertions.assertEquals(expected, actual);

        } catch (Exception ex) {
            Assertions.fail();
        }
    }

    @Test
    void getTypeCheckValid() {
        try {
            //arrange
            Item i = new Item("C1,Black Cat,2.25,Cat");

            //act
            String actual = i.getType();
            String expected = "Cat";

            //assert
            Assertions.assertEquals(expected, actual);

        } catch (Exception ex) {
            Assertions.fail();
        }
    }
    @Test
    void getTypeCheckInvalid() {
        try {
            //arrange
            Item i = new Item("C1,Black Cat,2.25,Cat");

            //act
            String actual = i.getName();
            String expected = "Black Cat";

            //assert
            Assertions.assertEquals(expected, actual);
        } catch (Exception ex) {
            Assertions.fail();
        }
    }

}