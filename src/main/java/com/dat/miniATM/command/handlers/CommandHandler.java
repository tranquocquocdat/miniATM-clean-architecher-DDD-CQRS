package com.dat.miniATM.command.handlers;

import com.dat.miniATM.application.usecases.account.AccountService;
import com.dat.miniATM.command.models.CreateAccountCommand;
import com.dat.miniATM.command.models.DepositMoneyCommand;
import com.dat.miniATM.command.models.WithdrawMoneyCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CommandHandler {
    @Autowired
    private AccountService accountService;

    public void handle(CreateAccountCommand command) {
        accountService.createAccount(command.getAccountId(), command.getAccountHolderName(), command.getInitialBalance());
    }

    public void handle(DepositMoneyCommand command) {
        accountService.depositMoney(command.getAccountId(), command.getAmount());
    }

    public void handle(WithdrawMoneyCommand command) {
        accountService.withdrawMoney(command.getAccountId(), command.getAmount());
    }
}