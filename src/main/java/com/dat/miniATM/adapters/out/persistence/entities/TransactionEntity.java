package com.dat.miniATM.adapters.out.persistence.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import java.util.UUID;
import lombok.Data;

@Data
@Entity
public class TransactionEntity {

    @Id
    private UUID id;
    private UUID accountId;
    private double amount;
    private String type; // "deposit" or "withdrawal"
}