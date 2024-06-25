package com.dat.miniATM.adapters.in.web;

import com.dat.miniATM.command.handlers.CreateTransactionCommandHandler;
import com.dat.miniATM.command.models.CreateTransactionCommand;
import java.util.UUID;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/commands/transactions")
public class TransactionCommandController {
    private final CreateTransactionCommandHandler createTransactionHandler;

    public TransactionCommandController(CreateTransactionCommandHandler createTransactionHandler) {
        this.createTransactionHandler = createTransactionHandler;
    }

    @PostMapping("/")
    public void createTransaction(@RequestParam UUID accountId, @RequestParam double amount, @RequestParam String type) {
        CreateTransactionCommand command = new CreateTransactionCommand(accountId, amount, type);
        createTransactionHandler.handle(command);
    }
}
