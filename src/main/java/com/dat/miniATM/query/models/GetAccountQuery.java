package com.dat.miniATM.query.models;

import java.util.UUID;
import lombok.Data;

@Data
public class GetAccountQuery {
    private final UUID accountId;
}