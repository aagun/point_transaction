package com.tujuhsembilan.pointtransaction.clients.enums;

import com.fasterxml.jackson.annotation.JsonAlias;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum TransactionDesc {
    @JsonAlias({"Bayar Listrik"})
    BAYAR_LISTRIK("Bayar Listrik"),

    @JsonAlias({"Beli Pulsa"})
    BELI_PULSA("Beli Pulsa");

    private final String value;
}
