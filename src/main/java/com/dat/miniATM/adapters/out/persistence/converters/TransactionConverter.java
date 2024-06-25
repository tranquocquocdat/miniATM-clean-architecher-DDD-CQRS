package com.dat.miniATM.adapters.out.persistence.converters;

import com.dat.miniATM.adapters.out.persistence.entities.TransactionEntity;
import com.dat.miniATM.domain.aggregates.Transaction;
import org.springframework.stereotype.Component;

@Component
public class TransactionConverter {

    public TransactionEntity toEntity(Transaction transaction) {
        TransactionEntity entity = new TransactionEntity();
        entity.setId(transaction.getId());
        entity.setAccountId(transaction.getAccountId());
        entity.setAmount(transaction.getAmount());
        entity.setType(transaction.getType());
        return entity;
    }

    public Transaction toDomain(TransactionEntity entity) {
        return new Transaction(entity.getId(), entity.getAccountId(), entity.getAmount(), entity.getType());
    }
}
