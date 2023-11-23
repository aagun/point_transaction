package com.tujuhsembilan.pointtransaction.clients.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum TransactionStatus {
    CREDIT("C"),
    DEBIT("D");

    private final String value;

}
