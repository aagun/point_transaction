package com.tujuhsembilan.pointtransaction.transaction.model.mongo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.util.ObjectUtils;

import java.time.LocalDate;

@Data
@Document(collection = "transactions")
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Transaction {
    @Id
    private String id;
    private Long accountId;
    private LocalDate transactionDate;
    private String description;
    private String debitCreditStatus;
    private Double amount;
    private Double balance;

    public boolean isEmpty() {
        return ObjectUtils.isEmpty(id) &&
                    ObjectUtils.isEmpty(accountId) &&
                    ObjectUtils.isEmpty(transactionDate) &&
                    ObjectUtils.isEmpty(description) &&
                    ObjectUtils.isEmpty(debitCreditStatus) &&
                    ObjectUtils.isEmpty(amount) &&
                    ObjectUtils.isEmpty(balance);
    }
}
