package com.dat.miniATM.domain.aggregates;

import com.dat.miniATM.command.models.CreateAccountCommand;
import com.dat.miniATM.domain.events.AccountCreatedEvent;
import com.dat.miniATM.domain.events.AccountEvent;
import com.dat.miniATM.domain.events.MoneyDepositedEvent;
import com.dat.miniATM.domain.events.MoneyWithdrawnEvent;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import lombok.Data;
import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.modelling.command.AggregateIdentifier;
import org.axonframework.modelling.command.AggregateLifecycle;
import org.axonframework.spring.stereotype.Aggregate;
import org.springframework.stereotype.Component;

import static org.axonframework.modelling.command.AggregateLifecycle.apply;

@Data
@Aggregate(snapshotTriggerDefinition = "buyerAggregateSnapshotTrigger")
public class AccountAggregate {
    @AggregateIdentifier
    private UUID accountId;
    private String accountHolderName;
    private double balance;


    public AccountAggregate() {
    }


    @CommandHandler
    public AccountAggregate(CreateAccountCommand command) {
        command.setAccountId(UUID.randomUUID());
        AggregateLifecycle.apply(new AccountCreatedEvent(command.getAccountId(), command.getAccountHolderName(), command.getInitialBalance()));
//        throw  new RuntimeException("deat letter queue insert");
    }

    @EventSourcingHandler
    public void on(AccountCreatedEvent event) {
        System.out.println("Handling AccountCreatedEvent: " + event.getAccountId());
        this.accountId = event.getAccountId();
        this.accountHolderName = event.getAccountHolderName();
        this.balance = event.getInitialBalance();
    }


}