package com.techelevator;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class ItemTest {

    @Test
    void makeSoundCatMew() {
        Item i = new Item("C1,Black Cat,2.25,Cat");
        String expected = "Meow, Meow, Meow!";
        String actual = i.getSound();
        Assertions.assertEquals(expected, actual);
    }
}