package com.tujuhsembilan.pointtransaction.transaction.dao;

import com.tujuhsembilan.pointtransaction.clients.model.pg.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface TransactionPostgresDao extends JpaRepository<Transaction, Long> {

    Optional<List<Transaction>> findByCustomerAccountIdAndTransactionDateBetween(Long accountId, LocalDate startDate, LocalDate endDate);
}
