package com.dat.miniATM.application.service.account;

import com.dat.miniATM.command.models.CreateAccountCommand;
import com.dat.miniATM.domain.aggregates.AccountAggregate;
import org.springframework.stereotype.Component;

@Component
public class AccountService {

    private CreateAccountCommand createAccountCommand;

    public void createAccount(CreateAccountCommand command) {
        AccountAggregate account = new AccountAggregate(command);

    }
}