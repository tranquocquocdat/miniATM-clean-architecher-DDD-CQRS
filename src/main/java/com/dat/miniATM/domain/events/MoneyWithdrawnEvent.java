package com.dat.miniATM.domain.events;

import java.util.UUID;
import lombok.Data;

@Data
public class MoneyWithdrawnEvent extends AccountEvent {
    private double amount;

    public MoneyWithdrawnEvent(UUID accountId, double amount) {
        this.amount = amount;
    }

    // Getters
}