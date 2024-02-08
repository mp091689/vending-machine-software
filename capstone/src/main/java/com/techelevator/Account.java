package com.techelevator;

public class Account {
    private Double balance = 0.0;

    public Double getBalance() {
        return balance;
    }

    public boolean deposit(int amount) {
        if (amount > 0) {
            this.balance += amount;
            return true;
        }
        return false;
    }

    public void withdraw(Double amount) {
        this.balance -= amount;
    }
}
