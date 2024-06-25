package com.dat.miniATM.adapters.out.persistence.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import java.util.UUID;
import lombok.Data;

@Data
@Entity
public class AccountEntity {

    @Id
    private UUID id;
    private double balance;
}