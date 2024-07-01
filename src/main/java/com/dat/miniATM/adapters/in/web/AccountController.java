package com.dat.miniATM.adapters.in.web;

import com.dat.miniATM.application.service.account.AccountService;
import com.dat.miniATM.command.models.CreateAccountCommand;
import lombok.AllArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@RequestMapping("/account")
public class AccountController {

    private final AccountService accountService;

    @PostMapping("/create")
    public void getBalance(@RequestBody @Validated CreateAccountCommand command) {
        accountService.createAccount(command);
    } 
}