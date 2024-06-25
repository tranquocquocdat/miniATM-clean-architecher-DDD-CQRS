package com.dat.miniATM.domain.aggregates;

import java.util.UUID;
import lombok.Data;

@Data
public class Account {
    private UUID id;
    private double balance;

    public Account(UUID id, double initialBalance) {
        this.id = id;
        this.balance = initialBalance;
    }

    public void withdraw(double amount) {
        if (amount > balance) {
            throw new IllegalArgumentException("Insufficient funds");
        }
        balance -= amount;
    }

    public void deposit(double amount) {
        balance += amount;
    }
}