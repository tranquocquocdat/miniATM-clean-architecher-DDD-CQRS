package com.dat.miniATM.query.handlers;

import com.dat.miniATM.application.usecases.account.GetAccountBalanceUseCase;
import com.dat.miniATM.query.models.GetAccountQuery;
import org.springframework.stereotype.Component;

@Component
public class GetAccountQueryHandler {
    private final GetAccountBalanceUseCase getAccountBalanceUseCase;

    public GetAccountQueryHandler(GetAccountBalanceUseCase getAccountBalanceUseCase) {
        this.getAccountBalanceUseCase = getAccountBalanceUseCase;
    }

    public double handle(GetAccountQuery query) {
        return getAccountBalanceUseCase.getBalance(query.getAccountId());
    }
}