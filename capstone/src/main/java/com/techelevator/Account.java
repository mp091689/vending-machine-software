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
    public BigDecimal returnCoins(){
        int balancePennies = balance.multiply(new BigDecimal(100)).intValue();
        int[] coins = new int[3];
        coins[0]=balancePennies/25;
        coins[1]=(balancePennies%25)/10;
        coins[2]=((balancePennies%25)%10)/5;
        System.out.println("Your return coins are " + coins[0] + "quarters");
        System.out.println("Your return coins are " + coins[1] + "dimes");
        System.out.println("Your return coins are " + coins[2] + "nickels");
        int payBack = (coins[0]*25 + coins[1]*10) + coins[2]*5;

        BigDecimal finalPennies = new BigDecimal(payBack);
        BigDecimal finalDollars = finalPennies.divide(new BigDecimal(100));
        withdraw(finalDollars);

        return finalDollars;
    }
}
