package com.dat.miniATM.domain.events;

import java.util.UUID;
import lombok.Data;

@Data
public class AccountCreatedEvent extends AccountEvent {
    private String accountHolderName;
    private double initialBalance;

    public AccountCreatedEvent(UUID accountId, String accountHolderName, double initialBalance) {
        super(accountId);
        this.accountHolderName = accountHolderName;
        this.initialBalance = initialBalance;
    }

    // Getters
}