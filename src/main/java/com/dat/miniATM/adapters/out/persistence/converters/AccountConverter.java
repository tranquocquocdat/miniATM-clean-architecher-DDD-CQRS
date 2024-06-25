package com.dat.miniATM.adapters.out.persistence.converters;

import com.dat.miniATM.adapters.out.persistence.entities.AccountEntity;
import com.dat.miniATM.domain.aggregates.Account;
import org.springframework.stereotype.Component;

@Component
public class AccountConverter {

    public AccountEntity toEntity(Account account) {
        AccountEntity entity = new AccountEntity();
        entity.setId(account.getId());
        entity.setBalance(account.getBalance());
        return entity;
    }

    public Account toDomain(AccountEntity entity) {
        return new Account(entity.getId(), entity.getBalance());
    }
}