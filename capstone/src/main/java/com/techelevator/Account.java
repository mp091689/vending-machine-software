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
        if (balance.subtract(amount).compareTo(BigDecimal.ZERO)<1) {
            return false;
        }
        balance = balance.subtract(amount);
        return true;
    }
}
