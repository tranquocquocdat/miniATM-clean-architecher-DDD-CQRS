package com.dat.miniATM.component.eventStore;

import com.dat.miniATM.domain.events.AccountEvent;
import java.util.*;
import org.springframework.stereotype.Component;

@Component
public class InMemoryEventStore implements EventStore {
    private Map<UUID, List<AccountEvent>> store = new HashMap<>();

    @Override
    public void saveEvents(UUID aggregateId, List<AccountEvent> events, int expectedVersion) {
        List<AccountEvent> eventStream = store.getOrDefault(aggregateId, new ArrayList<>());
        eventStream.addAll(events);
        store.put(aggregateId, eventStream);
    }

    @Override
    public List<AccountEvent> getEvents(UUID aggregateId) {
        return store.getOrDefault(aggregateId, new ArrayList<>());
    }
}