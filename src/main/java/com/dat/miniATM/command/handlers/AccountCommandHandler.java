package com.dat.miniATM.command.handlers;

import com.dat.miniATM.adapters.out.persistence.entities.AccountEntity;

import com.dat.miniATM.adapters.out.persistence.repositories.jpql.AccountRepository;
import com.dat.miniATM.domain.events.AccountCreatedEvent;

import lombok.AllArgsConstructor;
import org.axonframework.eventhandling.EventHandler;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class AccountCommandHandler {

    private final AccountRepository accountRepository;

    @EventHandler
    public void on(AccountCreatedEvent event) {
        AccountEntity accountEntity = new AccountEntity();
        accountEntity.setAccountHolderName(event.getAccountHolderName());
        accountRepository.save(accountEntity);
    }

}