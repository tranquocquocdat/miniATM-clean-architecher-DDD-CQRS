package com.dat.miniATM.application.usecases.transaction;

import com.dat.miniATM.domain.aggregates.Transaction;
import com.dat.miniATM.domain.repositories.TransactionRepository;
import java.util.UUID;
import org.springframework.stereotype.Service;

@Service
public class CreateTransactionUseCase {
    private final TransactionRepository transactionRepository;

    public CreateTransactionUseCase(TransactionRepository transactionRepository) {
        this.transactionRepository = transactionRepository;
    }

    public void createTransaction(UUID accountId, double amount, String type) {
        Transaction transaction = new Transaction(UUID.randomUUID(), accountId, amount, type);
        transactionRepository.save(transaction);
    }
}