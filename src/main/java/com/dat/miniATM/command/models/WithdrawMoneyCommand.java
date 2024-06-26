package com.dat.miniATM.command.models;

import java.util.UUID;
import lombok.Data;

@Data
public class WithdrawMoneyCommand {
    private UUID accountId;
    private double amount;

    // Constructors, getters, and setters
}