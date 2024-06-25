package com.dat.miniATM.adapters.in.web;

import com.dat.miniATM.domain.aggregates.Transaction;
import com.dat.miniATM.query.handlers.GetTransactionQueryHandler;
import com.dat.miniATM.query.models.GetTransactionQuery;
import java.util.UUID;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/queries/transactions")
public class TransactionQueryController {
    private final GetTransactionQueryHandler getTransactionHandler;

    public TransactionQueryController(GetTransactionQueryHandler getTransactionHandler) {
        this.getTransactionHandler = getTransactionHandler;
    }

    @GetMapping("/{transactionId}")
    public Transaction getTransactionDetails(@PathVariable UUID transactionId) {
        GetTransactionQuery query = new GetTransactionQuery(transactionId);
        return getTransactionHandler.handle(query);
    }
}