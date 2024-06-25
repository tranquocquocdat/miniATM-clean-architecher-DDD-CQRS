package com.dat.miniATM.adapters.in.web;

import com.dat.miniATM.command.handlers.DepositMoneyCommandHandler;
import com.dat.miniATM.command.handlers.WithdrawMoneyCommandHandler;
import com.dat.miniATM.command.models.DepositMoneyCommand;
import com.dat.miniATM.command.models.WithdrawMoneyCommand;
import java.util.UUID;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/commands/accounts")
public class AccountCommandController {
    private final WithdrawMoneyCommandHandler withdrawHandler;
    private final DepositMoneyCommandHandler depositHandler;

    public AccountCommandController(WithdrawMoneyCommandHandler withdrawHandler, DepositMoneyCommandHandler depositHandler) {
        this.withdrawHandler = withdrawHandler;
        this.depositHandler = depositHandler;
    }

    @PostMapping("/{accountId}/withdraw")
    public void withdraw(@PathVariable UUID accountId, @RequestParam double amount) {
        WithdrawMoneyCommand command = new WithdrawMoneyCommand(accountId, amount);
        withdrawHandler.handle(command);
    }

    @PostMapping("/{accountId}/deposit")
    public void deposit(@PathVariable UUID accountId, @RequestParam double amount) {
        DepositMoneyCommand command = new DepositMoneyCommand(accountId, amount);
        depositHandler.handle(command);
    }
}
