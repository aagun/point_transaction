package com.tujuhsembilan.pointtransaction.clients.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.tujuhsembilan.pointtransaction.clients.enums.TransactionDesc;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class PointRequest {
    private Long accountId;
    private TransactionDesc transactionDescription;
    private Double amount;
}
