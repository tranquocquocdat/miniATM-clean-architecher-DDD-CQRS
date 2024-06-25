package com.dat.miniATM.command.handlers;

import com.dat.miniATM.application.usecases.account.WithdrawMoneyUseCase;
import com.dat.miniATM.command.models.WithdrawMoneyCommand;
import org.springframework.stereotype.Component;

@Component
public class WithdrawMoneyCommandHandler {
    private final WithdrawMoneyUseCase withdrawMoneyUseCase;

    public WithdrawMoneyCommandHandler(WithdrawMoneyUseCase withdrawMoneyUseCase) {
        this.withdrawMoneyUseCase = withdrawMoneyUseCase;
    }

    public void handle(WithdrawMoneyCommand command) {
        withdrawMoneyUseCase.withdraw(command.getAccountId(), command.getAmount());
    }
}