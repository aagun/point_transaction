package com.tujuhsembilan.pointtransaction.model;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDate;

@Data
@Entity
@Table(name = "transactions")
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TransactionPostgres implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "transactions_gen")
    @SequenceGenerator(name = "transactions_gen", sequenceName = "transactions_seq", allocationSize = 1)
    @Column(name = "id", nullable = false)
    private Long id;

    @ManyToOne(targetEntity = CustomerPostgres.class, cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "account_id")
    private CustomerPostgres customer;

    @Column(name = "transaction_date", nullable = false)
    private LocalDate transactionDate;

    @Column(name = "description", nullable = false)
    private String description;

    @Column(name = "debit_credit_status", nullable = false)
    private String debitCreditStatus;

    @Column(name = "amount", nullable = false)
    private Double amount;

    @Column(name = "balance", nullable = false)
    private Double balance;

}
