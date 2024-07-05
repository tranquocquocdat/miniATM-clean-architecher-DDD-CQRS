package com.dat.miniATM.domain.events;

import java.util.UUID;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
public class AccountCreatedEvent extends AccountEvent {
    private UUID AccountId;
    private String accountHolderName;
    private double initialBalance;
}