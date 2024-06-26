package com.dat.miniATM.component.eventStore;

import com.dat.miniATM.adapters.out.persistence.entities.AccountEntity;
import com.dat.miniATM.domain.aggregates.AccountAggregate;
import com.dat.miniATM.domain.events.AccountEvent;
import com.dat.miniATM.domain.repositories.AccountRepository;
import jakarta.transaction.Transactional;
import java.util.List;
import java.util.UUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AccountProjection {
    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private EventStore eventStore;

    @Transactional
    public AccountEntity project(UUID accountId) {
        List<AccountEvent> eventStream = eventStore.getEvents(accountId);
        AccountAggregate account = new AccountAggregate(eventStream);
        AccountEntity entity = new AccountEntity();
        entity.setAccountId(account.getAccountId());
        entity.setAccountHolderName(account.getAccountHolderName());
        entity.setBalance(account.getBalance());
        return accountRepository.save(entity);
    }

    public AccountEntity getAccountById(UUID accountId) {
        return accountRepository.findById(accountId).orElse(null);
    }
}