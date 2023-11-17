package com.tujuhsembilan.pointtransaction.service;

import com.tujuhsembilan.pointtransaction.dao.TransactionPostgresDao;
import com.tujuhsembilan.pointtransaction.dto.ReportRequest;
import com.tujuhsembilan.pointtransaction.dto.ResponseDto;
import com.tujuhsembilan.pointtransaction.dto.TransactionResponse;
import com.tujuhsembilan.pointtransaction.helper.ResponseDtoHelper;
import com.tujuhsembilan.pointtransaction.model.TransactionPostgres;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
@Slf4j
public class TransactionPostgresService {

    private final TransactionPostgresDao transactionPostgresDao;

    public List<TransactionPostgres> getAllTransactions() {
        return transactionPostgresDao.findAll();
    }

    public List<TransactionPostgres> getAllTransactionsForReport(ReportRequest reportRequest) {
        return transactionPostgresDao.findByCustomerAccountIdAndTransactionDateBetween(
                reportRequest.getAccountId(),
                reportRequest.getStartDate(),
                reportRequest.getEndDate()
        ).orElse(Collections.emptyList());
    }

    public List<TransactionResponse> getAllMappedTransactions() {
        List<TransactionPostgres> transactions = transactionPostgresDao.findAll();
        return transactions.stream()
            .map(transaction -> TransactionResponse.builder()
                .id(String.valueOf(transaction.getId()))
                .accountId(transaction.getCustomer().getAccountId())
                .transactionDate(transaction.getTransactionDate())
                .description(transaction.getDescription())
                .debitCreditStatus(transaction.getDebitCreditStatus())
                .amount(transaction.getAmount())
                .build())
            .toList();
    }



}
