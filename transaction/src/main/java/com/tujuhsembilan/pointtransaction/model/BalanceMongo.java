package com.tujuhsembilan.pointtransaction.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.util.ObjectUtils;

import java.io.Serializable;
import java.util.Objects;

@Data
@Document(collection = "balances")
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class BalanceMongo implements Serializable {
    private String id;
    private Long accountId;
    private Double balance;

    public boolean isEmpty() {
        return ObjectUtils.isEmpty(id) && ObjectUtils.isEmpty(accountId) && ObjectUtils.isEmpty(balance);
    }
}
