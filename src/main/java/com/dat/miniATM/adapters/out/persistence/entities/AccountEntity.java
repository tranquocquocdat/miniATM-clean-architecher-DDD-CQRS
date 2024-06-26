package com.dat.miniATM.adapters.out.persistence.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import java.util.UUID;
import lombok.Data;

@Data
@Entity
public class AccountEntity {
        @Id
        private UUID accountId;
        private String accountHolderName;
        private double balance;

        // Constructors, getters, and setters
}