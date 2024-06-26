package com.dat.miniATM.domain.aggregates;

import com.dat.miniATM.domain.events.AccountCreatedEvent;
import com.dat.miniATM.domain.events.AccountEvent;
import com.dat.miniATM.domain.events.MoneyDepositedEvent;
import com.dat.miniATM.domain.events.MoneyWithdrawnEvent;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import lombok.Data;

@Data
public class AccountAggregate {
    private UUID accountId;
    private String accountHolderName;
    private double balance;

    private List<AccountEvent> changes = new ArrayList<>();

    public AccountAggregate() {}

    public AccountAggregate(List<AccountEvent> eventStream) {
        for (AccountEvent event : eventStream) {
            apply(event);
        }
    }

    public void createAccount(UUID accountId, String accountHolderName, double initialBalance) {
        applyAndStore(new AccountCreatedEvent(accountId, accountHolderName, initialBalance));
    }

    public void depositMoney(double amount) {
        applyAndStore(new MoneyDepositedEvent(accountId, amount));
    }

    public void withdrawMoney(double amount) {
        applyAndStore(new MoneyWithdrawnEvent(accountId, amount));
    }

    private void apply(AccountEvent event) {
        if (event instanceof AccountCreatedEvent) {
            AccountCreatedEvent e = (AccountCreatedEvent) event;
            this.accountId = e.getAccountId();
            this.accountHolderName = e.getAccountHolderName();
            this.balance = e.getInitialBalance();
        } else if (event instanceof MoneyDepositedEvent) {
            MoneyDepositedEvent e = (MoneyDepositedEvent) event;
            this.balance += e.getAmount();
        } else if (event instanceof MoneyWithdrawnEvent) {
            MoneyWithdrawnEvent e = (MoneyWithdrawnEvent) event;
            this.balance -= e.getAmount();
        }
    }

    private void applyAndStore(AccountEvent event) {
        apply(event);
        changes.add(event);
    }

    public List<AccountEvent> getUncommittedChanges() {
        return changes;
    }

    public void markChangesAsCommitted() {
        changes.clear();
    }
}