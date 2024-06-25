package com.dat.miniATM.query.models;

import java.util.UUID;
import lombok.Data;

@Data
public class GetTransactionQuery {
    private final UUID transactionId;
}