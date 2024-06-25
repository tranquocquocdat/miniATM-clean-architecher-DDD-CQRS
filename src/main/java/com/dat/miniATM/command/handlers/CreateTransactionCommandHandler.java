package com.dat.miniATM.command.handlers;

import com.dat.miniATM.application.usecases.transaction.CreateTransactionUseCase;
import com.dat.miniATM.command.models.CreateTransactionCommand;
import org.springframework.stereotype.Component;

@Component
public class CreateTransactionCommandHandler {
    private final CreateTransactionUseCase createTransactionUseCase;

    public CreateTransactionCommandHandler(CreateTransactionUseCase createTransactionUseCase) {
        this.createTransactionUseCase = createTransactionUseCase;
    }

    public void handle(CreateTransactionCommand command) {
        createTransactionUseCase.createTransaction(command.getAccountId(), command.getAmount(), command.getType());
    }
}
