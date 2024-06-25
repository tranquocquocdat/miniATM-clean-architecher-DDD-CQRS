package com.dat.miniATM.domain.repositories;

import com.dat.miniATM.domain.aggregates.Account;
import java.util.UUID;

public interface AccountRepository {
    Account findById(UUID id);
    void save(Account account);
}
