package com.dat.miniATM.domain.events;

import java.time.LocalDateTime;
import java.util.UUID;

public abstract class AccountEvent {
    private UUID accountId;
    private LocalDateTime timestamp;

    public AccountEvent(UUID accountId) {
        this.accountId = accountId;
        this.timestamp = LocalDateTime.now();
    }

    public UUID getAccountId() {
        return accountId;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }
}