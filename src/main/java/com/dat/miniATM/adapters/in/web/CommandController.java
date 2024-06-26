package com.dat.miniATM.adapters.in.web;

import com.dat.miniATM.command.handlers.CommandHandler;
import com.dat.miniATM.command.models.CreateAccountCommand;
import com.dat.miniATM.command.models.DepositMoneyCommand;
import com.dat.miniATM.command.models.WithdrawMoneyCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/commands")
public class CommandController {
    @Autowired
    private CommandHandler commandHandler;

    @PostMapping("/createAccount")
    public void createAccount(@RequestBody CreateAccountCommand command) {
        commandHandler.handle(command);
    }

    @PostMapping("/depositMoney")
    public void depositMoney(@RequestBody DepositMoneyCommand command) {
        commandHandler.handle(command);
    }

    @PostMapping("/withdrawMoney")
    public void withdrawMoney(@RequestBody WithdrawMoneyCommand command) {
        commandHandler.handle(command);
    }
}