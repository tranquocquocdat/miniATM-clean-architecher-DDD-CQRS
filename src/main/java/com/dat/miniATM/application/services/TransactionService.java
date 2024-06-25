package com.dat.miniATM.application.services;

import com.dat.miniATM.application.usecases.transaction.CreateTransactionUseCase;
import com.dat.miniATM.application.usecases.transaction.GetTransactionDetailsUseCase;
import com.dat.miniATM.domain.aggregates.Transaction;
import java.util.UUID;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class TransactionService {
    private final CreateTransactionUseCase createTransactionUseCase;
    private final GetTransactionDetailsUseCase getTransactionDetailsUseCase;

    public void createTransaction(UUID accountId, double amount, String type) {
        createTransactionUseCase.createTransaction(accountId, amount, type);
    }

    public Transaction getTransactionDetails(UUID transactionId) {
        return getTransactionDetailsUseCase.getTransactionDetails(transactionId);
    }
}