package com.dat.miniATM.application.services;

import com.dat.miniATM.application.usecases.account.DepositMoneyUseCase;
import com.dat.miniATM.application.usecases.account.GetAccountBalanceUseCase;
import com.dat.miniATM.application.usecases.account.WithdrawMoneyUseCase;
import java.util.UUID;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class AccountService {
    private final DepositMoneyUseCase depositMoneyUseCase;
    private final WithdrawMoneyUseCase withdrawMoneyUseCase;
    private final GetAccountBalanceUseCase getAccountBalanceUseCase;


    public void deposit(UUID accountId, double amount) {
        depositMoneyUseCase.deposit(accountId, amount);
    }

    public void withdraw(UUID accountId, double amount) {
        withdrawMoneyUseCase.withdraw(accountId, amount);
    }

    public double getBalance(UUID accountId) {
        return getAccountBalanceUseCase.getBalance(accountId);
    }
}