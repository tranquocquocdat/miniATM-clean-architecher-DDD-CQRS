package com.dat.miniATM.adapters.out.persistence.repositories;

import com.dat.miniATM.adapters.out.persistence.converters.TransactionConverter;
import com.dat.miniATM.adapters.out.persistence.entities.TransactionEntity;
import com.dat.miniATM.domain.aggregates.Transaction;
import com.dat.miniATM.domain.repositories.TransactionRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import java.util.UUID;
import org.springframework.stereotype.Repository;

@Repository
public class TransactionRepositoryImpl implements TransactionRepository {

    @PersistenceContext
    private EntityManager entityManager;

    private final TransactionConverter transactionConverter;

    public TransactionRepositoryImpl(TransactionConverter transactionConverter) {
        this.transactionConverter = transactionConverter;
    }

    @Override
    public Transaction findById(UUID id) {
        TransactionEntity transactionEntity = entityManager.find(TransactionEntity.class, id);
        if (transactionEntity == null) {
            return null;
        }
        return transactionConverter.toDomain(transactionEntity);
    }

    @Override
    public void save(Transaction transaction) {
        TransactionEntity transactionEntity = transactionConverter.toEntity(transaction);
        entityManager.merge(transactionEntity);
    }
}