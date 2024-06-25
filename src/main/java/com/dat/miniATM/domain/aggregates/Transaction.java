package com.dat.miniATM.domain.aggregates;

import java.util.UUID;
import lombok.Data;

@Data
public class Transaction {
    private UUID id;
    private UUID accountId;
    private double amount;
    private String type; // "deposit" or "withdrawal"

    public Transaction(UUID id, UUID accountId, double amount, String type) {
        this.id = id;
        this.accountId = accountId;
        this.amount = amount;
        this.type = type;
    }
}
