package com.dat.miniATM.adapters.out.persistence.repositories.EnityManager;


import com.dat.miniATM.adapters.out.persistence.entities.AccountEntity;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import java.util.Optional;
import org.springframework.stereotype.Repository;

@Repository
public class AccountManagerRepository {

    @PersistenceContext
    private EntityManager entityManager;

        public Optional<AccountEntity> findAccountById(Long id) {
            AccountEntity accountEntity = entityManager.find(AccountEntity.class, id);
            return Optional.ofNullable(accountEntity);
        }

}
