package com.tujuhsembilan.pointtransaction.transaction.dto;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.tujuhsembilan.pointtransaction.clients.helper.LocalDateToLocalDateTimeDeserializer;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TransactionRequest {
    private Long accountId;
    @JsonDeserialize(using = LocalDateToLocalDateTimeDeserializer.class)
    private LocalDate transactionDate;
    private String description;
    private String debitCreditStatus;
    private Double amount;
}
