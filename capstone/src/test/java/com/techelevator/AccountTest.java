package com.techelevator;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

public class AccountTest {
    @Test
    void checkDepositValidPositive(){
        //arrange
        Account acc = new Account();
        //act
        //assert
        Assertions.assertTrue(acc.deposit(12));
    }
    @Test
    void checkDepositValidZero(){
        //arrange
        Account acc = new Account();
        //act
        //assert
        Assertions.assertFalse(acc.deposit(0));
    }
    @Test
    void checkDepositValidNegative(){
        //arrange
        Account acc = new Account();
        //act
        //assert
        Assertions.assertFalse(acc.deposit(-10));
    }
    @Test
    void checkDepositValidBalance(){
        //arrange
        BigDecimal expected = new BigDecimal(12);
        Account acc = new Account();
        //act
        acc.deposit(expected.intValue());
        //assert
        Assertions.assertEquals(expected, acc.getBalance());
    }
    @Test
    void checkWithdrawalPositiveZeroBalance(){
        //arrange
        Account acc = new Account();
        //act
        //assert
        Assertions.assertFalse(acc.withdraw(BigDecimal.valueOf(2.35)));
    }
    @Test
    void checkWithdrawalPositiveWithBalance(){
        //arrange
        Account acc = new Account();
        //act
        acc.deposit(5);
        //assert
        Assertions.assertTrue(acc.withdraw(BigDecimal.valueOf(2.35)));
    }
    @Test
    void checkWithdrawalPositiveMoreBalance(){
        //arrange
        Account acc = new Account();
        //act
        acc.deposit(5);
        //assert
        Assertions.assertFalse(acc.withdraw(BigDecimal.valueOf(12.35)));
    }
    @Test
    void checkWithdrawalPositiveMoreBalanceCheckBalance(){
        //arrange
        BigDecimal expected = new BigDecimal("2.45");  ///*****
        Account acc = new Account();
        //act
        acc.deposit(5);
        acc.withdraw(BigDecimal.valueOf(2.55));
        //assert
        Assertions.assertEquals(expected,acc.getBalance());
    }
    @Test
    void checkWithdrawalPositiveMoreBalanceCheckLessBalance(){
        //arrange
        BigDecimal expected = new BigDecimal("1");
        Account acc = new Account();
        //act
        acc.deposit(1);
        acc.withdraw(BigDecimal.valueOf(2.55));
        //assert
        Assertions.assertEquals(expected,acc.getBalance());
    }
    @Test
    void checkReturnCoinsBalance(){
        //arrange
        BigDecimal expected = new BigDecimal("2.45");
        Account acc = new Account();
        //act
        acc.deposit(5);
        acc.withdraw(BigDecimal.valueOf(2.52));
        BigDecimal actual = acc.returnCoins();
        //assert
        Assertions.assertEquals(expected,actual);
    }
    @Test
    void checkReturnCoinsReturned(){
        //arrange
        BigDecimal expected = new BigDecimal("0.02");
        Account acc = new Account();
        //act
        acc.deposit(5);
        acc.withdraw(BigDecimal.valueOf(2.53));
        BigDecimal actual = acc.returnCoins();
        //assert
        Assertions.assertEquals(expected,acc.getBalance());
    }
    @Test
    void checkValidBill1(){
        //arrange
        Account acc = new Account();
        //act
        //assert
        Assertions.assertTrue(acc.isValidBill(1));
    }

    @Test
    void checkValidBill5(){
        //arrange
        Account acc = new Account();
        //act
        //assert
        Assertions.assertTrue(acc.isValidBill(5));
    }

    @Test
    void checkValidBill10(){
        //arrange
        Account acc = new Account();
        //act
        //assert
        Assertions.assertTrue(acc.isValidBill(10));
    }

    @Test
    void checkValidBill20(){
        //arrange
        Account acc = new Account();
        //act
        //assert
        Assertions.assertTrue(acc.isValidBill(20));
    }

    @Test
    void checkValidBill0(){
        //arrange
        Account acc = new Account();
        //act
        //assert
        Assertions.assertFalse(acc.isValidBill(0));
    }

    @Test
    void checkValidBill7(){
        //arrange
        Account acc = new Account();
        //act
        //assert
        Assertions.assertFalse(acc.isValidBill(7));
    }
    @Test
    void checkValidBillMinus5(){
        //arrange
        Account acc = new Account();
        //act
        //assert
        Assertions.assertFalse(acc.isValidBill(-5));
    }
}
