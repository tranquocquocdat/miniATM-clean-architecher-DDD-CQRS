package com.dat.miniATM.command.models;

import java.util.UUID;
import lombok.Data;

@Data
public class DepositMoneyCommand {
    private UUID accountId;
    private double amount;

    // Constructors, getters, and setters
}