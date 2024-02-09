package com.techelevator;

import java.math.BigDecimal;

public class Account {
    private BigDecimal balance;

    public Account() {
        this.balance = new BigDecimal(0);
        this.balance.setScale(2);
    }

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
        System.out.printf("Here is your change:");
        System.out.println("    Quarters: " + quarters);
        System.out.println("    Dimes:    " + dimes);
        System.out.println("    Nickels:  " + nickels);
        int payBack = (quarters * 25 + dimes * 10) + nickels * 5;

        BigDecimal finalPennies = new BigDecimal(payBack);
        BigDecimal finalDollars = finalPennies.divide(new BigDecimal(100));
        withdraw(finalDollars);

        return finalDollars;
    }

    public boolean isValidBill(int deposit) {
        for (int validBill : new int[]{1, 5, 10, 20}) {
            if (validBill == deposit) {
                return true;
            }
        }
        return false;
    }
}
