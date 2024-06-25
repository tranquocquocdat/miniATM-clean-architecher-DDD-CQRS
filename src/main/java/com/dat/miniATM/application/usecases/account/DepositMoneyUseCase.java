package com.dat.miniATM.application.usecases.account;

import com.dat.miniATM.domain.aggregates.Account;
import com.dat.miniATM.domain.repositories.AccountRepository;
import java.util.UUID;
import org.springframework.stereotype.Service;

@Service
public class DepositMoneyUseCase {
    private final AccountRepository accountRepository;

    public DepositMoneyUseCase(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    public void deposit(UUID accountId, double amount) {
        Account account = accountRepository.findById(accountId);
        if (account == null) {
            throw new IllegalArgumentException("Account not found");
        }
        account.deposit(amount);
        accountRepository.save(account);
    }
}