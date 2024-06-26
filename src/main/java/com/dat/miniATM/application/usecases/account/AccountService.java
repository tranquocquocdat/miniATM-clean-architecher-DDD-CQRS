package com.dat.miniATM.application.usecases.account;

import com.dat.miniATM.component.eventStore.EventStore;
import com.dat.miniATM.domain.aggregates.AccountAggregate;
import com.dat.miniATM.domain.events.AccountEvent;
import java.util.List;
import java.util.UUID;
import org.springframework.stereotype.Component;

@Component
public class AccountService {
    private final EventStore eventStore;

    public AccountService(EventStore eventStore) {
        this.eventStore = eventStore;
    }

    public void createAccount(UUID accountId, String accountHolderName, double initialBalance) {
        AccountAggregate account = new AccountAggregate();
        account.createAccount(accountId, accountHolderName, initialBalance);
        eventStore.saveEvents(accountId, account.getUncommittedChanges(), 0);
        account.markChangesAsCommitted();
    }

    public void depositMoney(UUID accountId, double amount) {
        List<AccountEvent> eventStream = eventStore.getEvents(accountId);
        AccountAggregate account = new AccountAggregate(eventStream);
        account.depositMoney(amount);
        eventStore.saveEvents(accountId, account.getUncommittedChanges(), eventStream.size());
        account.markChangesAsCommitted();
    }

    public void withdrawMoney(UUID accountId, double amount) {
        List<AccountEvent> eventStream = eventStore.getEvents(accountId);
        AccountAggregate account = new AccountAggregate(eventStream);
        account.withdrawMoney(amount);
        eventStore.saveEvents(accountId, account.getUncommittedChanges(), eventStream.size());
        account.markChangesAsCommitted();
    }
}