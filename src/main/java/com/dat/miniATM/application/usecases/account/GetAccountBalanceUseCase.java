package com.dat.miniATM.application.usecases.account;

import com.dat.miniATM.domain.aggregates.Account;
import com.dat.miniATM.domain.repositories.AccountRepository;
import java.util.UUID;
import org.springframework.stereotype.Service;

@Service
public class GetAccountBalanceUseCase {
    private final AccountRepository accountRepository;

    public GetAccountBalanceUseCase(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    public double getBalance(UUID accountId) {
        Account account = accountRepository.findById(accountId);
        if (account == null) {
            throw new IllegalArgumentException("Account not found");
        }
        return account.getBalance();
    }
}