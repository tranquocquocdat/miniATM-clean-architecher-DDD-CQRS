package com.dat.miniATM.command.handlers;

import com.dat.miniATM.application.usecases.account.DepositMoneyUseCase;
import com.dat.miniATM.command.models.DepositMoneyCommand;
import org.springframework.stereotype.Component;

@Component
public class DepositMoneyCommandHandler {
    private final DepositMoneyUseCase depositMoneyUseCase;

    public DepositMoneyCommandHandler(DepositMoneyUseCase depositMoneyUseCase) {
        this.depositMoneyUseCase = depositMoneyUseCase;
    }

    public void handle(DepositMoneyCommand command) {
        depositMoneyUseCase.deposit(command.getAccountId(), command.getAmount());
    }
}