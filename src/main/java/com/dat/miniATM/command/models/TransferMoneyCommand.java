package com.dat.miniATM.command.models;

import java.util.UUID;
import lombok.Data;

@Data
public class TransferMoneyCommand {
    private final UUID fromAccountId;
    private final UUID toAccountId;
    private final double amount;
}