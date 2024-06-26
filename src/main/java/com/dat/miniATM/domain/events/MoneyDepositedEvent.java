package com.dat.miniATM.domain.events;

import java.util.UUID;
import lombok.Data;

@Data
public class MoneyDepositedEvent extends AccountEvent {
    private double amount;

    public MoneyDepositedEvent(UUID accountId, double amount) {
        super(accountId);
        this.amount = amount;
    }

    // Getters
}