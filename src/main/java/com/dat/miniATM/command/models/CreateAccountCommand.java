package com.dat.miniATM.command.models;

import java.util.UUID;
import lombok.Data;

@Data
public class CreateAccountCommand {
    private UUID accountId;
    private String accountHolderName;
    private double initialBalance;

    // Constructors, getters, and setters
}