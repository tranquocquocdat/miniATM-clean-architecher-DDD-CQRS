package com.dat.miniATM.application.usecases.account;

import com.dat.miniATM.domain.aggregates.Account;
import com.dat.miniATM.domain.repositories.AccountRepository;
import java.util.UUID;
import org.springframework.stereotype.Service;

@Service
public class WithdrawMoneyUseCase {
    private final AccountRepository accountRepository;

    public WithdrawMoneyUseCase(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    public void withdraw(UUID accountId, double amount) {
        Account account = accountRepository.findById(accountId);
        if (account == null) {
            throw new IllegalArgumentException("Account not found");
        }
        account.withdraw(amount);
        accountRepository.save(account);
    }
}