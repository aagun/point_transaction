package com.tujuhsembilan.pointtransaction.transaction.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TransactionResponse {
    private String id;
    private Long accountId;
    private LocalDate transactionDate;
    private String description;
    private String debitCreditStatus;
    private Double amount;
    private Double balance;
}
