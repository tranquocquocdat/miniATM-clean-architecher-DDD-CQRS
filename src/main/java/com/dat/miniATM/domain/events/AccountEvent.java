package com.dat.miniATM.domain.events;

import java.time.LocalDateTime;
import java.util.UUID;

public abstract class AccountEvent {
    private UUID accountId;
    private LocalDateTime timestamp;

    public UUID getAccountId() {
        return accountId;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }
}