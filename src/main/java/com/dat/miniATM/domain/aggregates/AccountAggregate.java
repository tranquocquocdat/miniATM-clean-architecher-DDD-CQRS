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
import org.axonframework.modelling.command.AggregateIdentifier;
import org.axonframework.modelling.command.AggregateLifecycle;
import org.axonframework.spring.stereotype.Aggregate;
import org.springframework.stereotype.Component;

import static org.axonframework.modelling.command.AggregateLifecycle.apply;

@Data
@Aggregate
@Component
public class AccountAggregate {
    @AggregateIdentifier
    private UUID accountId;
    private String accountHolderName;
    private double balance;

    public AccountAggregate(CreateAccountCommand command) {
        AggregateLifecycle.apply(command);
    }

}