package com.techelevator;

import java.math.BigDecimal;

public class Account {
    private BigDecimal balance = new BigDecimal("0.0");

    public BigDecimal getBalance() {
        return balance;
    }

    public boolean deposit(int amount) {
        if (amount > 0) {
            balance = balance.add(BigDecimal.valueOf(amount));
            return true;
        }
        return false;
    }

    public boolean withdraw(BigDecimal amount) {
        if (balance.subtract(amount).compareTo(BigDecimal.ZERO) < 0) {
            return false;
        }
        balance = balance.subtract(amount);
        return true;
    }

    public BigDecimal returnCoins() {
        int balancePennies = balance.multiply(new BigDecimal(100)).intValue();
        int quarters = balancePennies / 25;
        int dimes = (balancePennies % 25) / 10;
        int nickels = ((balancePennies % 25) % 10) / 5;
        System.out.println("Your return coins are " + quarters + "quarters");
        System.out.println("Your return coins are " + dimes + "dimes");
        System.out.println("Your return coins are " + nickels + "nickels");
        int payBack = (quarters * 25 + dimes * 10) + nickels * 5;

        BigDecimal finalPennies = new BigDecimal(payBack);
        BigDecimal finalDollars = finalPennies.divide(new BigDecimal(100));
        withdraw(finalDollars);

        return finalDollars;
    }
}
