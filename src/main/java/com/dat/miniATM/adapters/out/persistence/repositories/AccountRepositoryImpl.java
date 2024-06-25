package com.dat.miniATM.adapters.out.persistence.repositories;

import com.dat.miniATM.adapters.out.persistence.converters.AccountConverter;
import com.dat.miniATM.adapters.out.persistence.entities.AccountEntity;
import com.dat.miniATM.domain.aggregates.Account;
import com.dat.miniATM.domain.repositories.AccountRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import java.util.UUID;
import org.springframework.stereotype.Repository;

@Repository
public class AccountRepositoryImpl implements AccountRepository {

    @PersistenceContext
    private EntityManager entityManager;

    private final AccountConverter accountConverter;

    public AccountRepositoryImpl(AccountConverter accountConverter) {
        this.accountConverter = accountConverter;
    }

    @Override
    public Account findById(UUID id) {
        AccountEntity accountEntity = entityManager.find(AccountEntity.class, id);
        if (accountEntity == null) {
            return null;
        }
        return accountConverter.toDomain(accountEntity);
    }

    @Override
    public void save(Account account) {
        AccountEntity accountEntity = accountConverter.toEntity(account);
        entityManager.merge(accountEntity);
    }
}
