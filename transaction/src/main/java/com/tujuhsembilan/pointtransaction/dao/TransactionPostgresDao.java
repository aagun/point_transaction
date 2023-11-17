package com.tujuhsembilan.pointtransaction.dao;

import com.tujuhsembilan.pointtransaction.model.TransactionPostgres;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface TransactionPostgresDao extends JpaRepository<TransactionPostgres, Long> {

    Optional<List<TransactionPostgres>> findByTransactionDateBetween(LocalDate transactionDate, LocalDate transactionDate2);

    Optional<List<TransactionPostgres>> findByCustomerAccountIdAndTransactionDateBetween(Long accountId, LocalDate startDate, LocalDate endDate);
}
