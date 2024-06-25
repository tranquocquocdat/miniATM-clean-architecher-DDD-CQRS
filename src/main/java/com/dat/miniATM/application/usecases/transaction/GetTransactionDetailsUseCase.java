package com.dat.miniATM.application.usecases.transaction;

import com.dat.miniATM.domain.aggregates.Transaction;
import com.dat.miniATM.domain.repositories.TransactionRepository;
import java.util.UUID;
import org.springframework.stereotype.Service;

@Service
public class GetTransactionDetailsUseCase {
    private final TransactionRepository transactionRepository;

    public GetTransactionDetailsUseCase(TransactionRepository transactionRepository) {
        this.transactionRepository = transactionRepository;
    }

    public Transaction getTransactionDetails(UUID transactionId) {
        return transactionRepository.findById(transactionId);
    }
}