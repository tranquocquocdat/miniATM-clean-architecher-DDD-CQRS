package com.dat.miniATM.domain.repositories;

import com.dat.miniATM.domain.aggregates.Transaction;
import java.util.UUID;

public interface TransactionRepository {
    Transaction findById(UUID id);
    void save(Transaction transaction);
}