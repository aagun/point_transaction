package com.tujuhsembilan.pointtransaction.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@Builder
@Entity
@Table(name = "customers")
@NoArgsConstructor
@AllArgsConstructor
public class CustomerPostgres implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "customers_gen")
    @SequenceGenerator(name = "customers_gen", sequenceName = "customers_seq", allocationSize = 1)
    @Column(name = "account_id", nullable = false)
    private Long accountId;

    private String name;
}
