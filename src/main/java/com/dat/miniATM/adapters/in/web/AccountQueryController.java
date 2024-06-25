package com.dat.miniATM.adapters.in.web;

import com.dat.miniATM.query.handlers.GetAccountQueryHandler;
import com.dat.miniATM.query.models.GetAccountQuery;
import java.util.UUID;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/queries/accounts")
public class AccountQueryController {
    private final GetAccountQueryHandler getAccountHandler;

    public AccountQueryController(GetAccountQueryHandler getAccountHandler) {
        this.getAccountHandler = getAccountHandler;
    }

    @GetMapping("/{accountId}/balance")
    public double getBalance(@PathVariable UUID accountId) {
        GetAccountQuery query = new GetAccountQuery(accountId);
        return getAccountHandler.handle(query);
    }
}