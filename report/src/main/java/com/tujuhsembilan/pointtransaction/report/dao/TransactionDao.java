package com.tujuhsembilan.pointtransaction.report.dao;

import com.tujuhsembilan.pointtransaction.clients.model.pg.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface TransactionDao extends JpaRepository<Transaction, Long> {

    Optional<List<Transaction>> findByCustomer_AccountIdAndTransactionDateBetween(Long accountId, LocalDate transactionDate, LocalDate transactionDate2);
}
