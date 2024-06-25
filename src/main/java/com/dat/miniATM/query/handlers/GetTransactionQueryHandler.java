package com.dat.miniATM.query.handlers;

import com.dat.miniATM.application.usecases.transaction.GetTransactionDetailsUseCase;
import com.dat.miniATM.domain.aggregates.Transaction;
import com.dat.miniATM.query.models.GetTransactionQuery;
import org.springframework.stereotype.Component;

@Component
public class GetTransactionQueryHandler {
    private final GetTransactionDetailsUseCase getTransactionDetailsUseCase;

    public GetTransactionQueryHandler(GetTransactionDetailsUseCase getTransactionDetailsUseCase) {
        this.getTransactionDetailsUseCase = getTransactionDetailsUseCase;
    }

    public Transaction handle(GetTransactionQuery query) {
        return getTransactionDetailsUseCase.getTransactionDetails(query.getTransactionId());
    }
}