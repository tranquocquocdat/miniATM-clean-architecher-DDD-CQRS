package com.dat.miniATM.command.models;

import java.util.UUID;
import lombok.Data;

@Data
public class CreateTransactionCommand {
    private final UUID accountId;
    private final double amount;
    private final String type;
}