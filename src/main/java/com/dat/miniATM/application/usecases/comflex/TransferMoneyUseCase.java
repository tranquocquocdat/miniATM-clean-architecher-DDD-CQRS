package com.dat.miniATM.application.usecases.comflex;

import com.dat.miniATM.domain.aggregates.Account;
import com.dat.miniATM.domain.aggregates.Transaction;
import com.dat.miniATM.domain.repositories.AccountRepository;
import com.dat.miniATM.domain.repositories.TransactionRepository;
import jakarta.transaction.Transactional;
import java.util.UUID;
import org.springframework.stereotype.Service;

@Service
public class TransferMoneyUseCase {
    private final AccountRepository accountRepository;
    private final TransactionRepository transactionRepository;

    public TransferMoneyUseCase(AccountRepository accountRepository, TransactionRepository transactionRepository) {
        this.accountRepository = accountRepository;
        this.transactionRepository = transactionRepository;
    }

    @Transactional
    public void transfer(UUID fromAccountId, UUID toAccountId, double amount) {
        Account fromAccount = accountRepository.findById(fromAccountId);
        Account toAccount = accountRepository.findById(toAccountId);

        if (fromAccount == null || toAccount == null) {
            throw new IllegalArgumentException("One or both accounts not found");
        }

        fromAccount.withdraw(amount);
        toAccount.deposit(amount);

        accountRepository.save(fromAccount);
        accountRepository.save(toAccount);

        Transaction debitTransaction = new Transaction(UUID.randomUUID(), fromAccountId, amount, "debit");
        Transaction creditTransaction = new Transaction(UUID.randomUUID(), toAccountId, amount, "credit");

        transactionRepository.save(debitTransaction);
        transactionRepository.save(creditTransaction);
    }
}