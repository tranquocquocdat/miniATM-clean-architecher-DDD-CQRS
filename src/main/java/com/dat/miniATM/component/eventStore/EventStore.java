package com.dat.miniATM.component.eventStore;

import com.dat.miniATM.domain.events.AccountEvent;
import java.util.List;
import java.util.UUID;

public interface EventStore {
    void saveEvents(UUID aggregateId, List<AccountEvent> events, int expectedVersion);
    List<AccountEvent> getEvents(UUID aggregateId);
}