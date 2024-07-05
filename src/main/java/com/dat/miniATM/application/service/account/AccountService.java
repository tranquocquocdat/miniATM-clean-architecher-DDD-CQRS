package com.dat.miniATM.application.service.account;

import com.dat.miniATM.command.models.CreateAccountCommand;
import com.dat.miniATM.domain.aggregates.AccountAggregate;
import lombok.AllArgsConstructor;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class AccountService {

    private final CommandGateway commandGateway;

    public void createAccount(CreateAccountCommand command) {
        commandGateway.sendAndWait(command);

    }
}