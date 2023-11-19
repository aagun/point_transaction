package com.tujuhsembilan.pointtransaction.transaction.service;

import com.tujuhsembilan.pointtransaction.clients.enums.TransactionStatus;
import com.tujuhsembilan.pointtransaction.transaction.dao.BalanceMongoDao;
import com.tujuhsembilan.pointtransaction.transaction.dao.TransactionMongoDao;
import com.tujuhsembilan.pointtransaction.transaction.dto.TransactionRequest;
import com.tujuhsembilan.pointtransaction.transaction.dto.TransactionResponse;
import com.tujuhsembilan.pointtransaction.transaction.model.mongo.Balance;
import com.tujuhsembilan.pointtransaction.transaction.model.mongo.Transaction;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@AllArgsConstructor
public class TransactionMongoService {

    private final BalanceMongoDao balanceMongoDao;

    private final TransactionMongoDao transactionMongoDao;

    public Transaction saveTransaction(TransactionRequest transactionRequest) {

        Balance currentBalance = balanceMongoDao.findByAccountId(transactionRequest.getAccountId())
                .orElse(Balance
                        .builder()
                        .accountId(transactionRequest.getAccountId())
                        .balance(0D)
                        .build());

        if (TransactionStatus.CREDIT.getValue().equals(transactionRequest.getDebitCreditStatus())) {
            currentBalance.setBalance(
                    currentBalance.getBalance() + transactionRequest.getAmount()
            );
        } else {
            currentBalance.setBalance(
                    currentBalance.getBalance() - transactionRequest.getAmount()
            );
        }

        currentBalance = balanceMongoDao.save(currentBalance);

        return transactionMongoDao.save(
                Transaction.builder()
                        .accountId(transactionRequest.getAccountId())
                        .transactionDate(transactionRequest.getTransactionDate())
                        .description(transactionRequest.getDescription())
                        .debitCreditStatus(transactionRequest.getDebitCreditStatus())
                        .amount(transactionRequest.getAmount())
                        .balance(currentBalance.getBalance())
                        .build()
        );
    }

    public List<TransactionResponse> getAllMappedTransactions() {
        List<Transaction> transactions = transactionMongoDao.findAll();
        return transactions.stream()
                .map(transaction -> TransactionResponse.builder()
                        .id(transaction.getId())
                        .accountId(transaction.getAccountId())
                        .transactionDate(transaction.getTransactionDate())
                        .description(transaction.getDescription())
                        .debitCreditStatus(transaction.getDebitCreditStatus())
                        .amount(transaction.getAmount())
                        .build())
                .toList();
    }
}
