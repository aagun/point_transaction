package com.tujuhsembilan.pointtransaction.transaction.service;

import com.tujuhsembilan.pointtransaction.clients.dto.ReportRequest;
import com.tujuhsembilan.pointtransaction.clients.model.pg.Transaction;
import com.tujuhsembilan.pointtransaction.transaction.dao.TransactionPostgresDao;
import com.tujuhsembilan.pointtransaction.transaction.dto.TransactionResponse;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
@AllArgsConstructor
@Slf4j
public class TransactionPostgresService {

    private final TransactionPostgresDao transactionPostgresDao;

    public List<Transaction> getAllTransactions() {
        return transactionPostgresDao.findAll();
    }

    public List<Transaction> getAllTransactionsForReport(ReportRequest reportRequest) {
        return transactionPostgresDao.findByCustomerAccountIdAndTransactionDateBetween(reportRequest.getAccountId(), reportRequest.getStartDate(), reportRequest.getEndDate()).orElse(Collections.emptyList());
    }

    public List<TransactionResponse> getAllMappedTransactions() {
        List<Transaction> transactions = transactionPostgresDao.findAll();
        return transactions.stream()
                .map(transaction -> TransactionResponse.builder()
                        .id(String.valueOf(transaction.getId()))
                        .accountId(transaction.getCustomer().getAccountId())
                        .transactionDate(transaction.getTransactionDate())
                        .description(transaction.getDescription())
                        .debitCreditStatus(transaction.getDebitCreditStatus())
                        .amount(transaction.getAmount())
                        .build()
                ).toList();
    }


}
