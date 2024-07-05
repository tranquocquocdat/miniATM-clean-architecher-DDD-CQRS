package com.dat.miniATM.domain.events;

import java.util.UUID;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class MoneyDepositedEvent extends AccountEvent {
    private double amount;

    // Getters
}