package com.dat.miniATM.command.handlers;

import com.dat.miniATM.application.usecases.comflex.TransferMoneyUseCase;
import com.dat.miniATM.command.models.TransferMoneyCommand;
import org.springframework.stereotype.Component;

@Component
public class TransferMoneyCommandHandler {
    private final TransferMoneyUseCase transferMoneyUseCase;

    public TransferMoneyCommandHandler(TransferMoneyUseCase transferMoneyUseCase) {
        this.transferMoneyUseCase = transferMoneyUseCase;
    }

    public void handle(TransferMoneyCommand command) {
        transferMoneyUseCase.transfer(command.getFromAccountId(), command.getToAccountId(), command.getAmount());
    }
}