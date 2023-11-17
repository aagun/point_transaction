package com.tujuhsembilan.pointtransaction.service;

import com.tujuhsembilan.pointtransaction.dao.BalanceMongoDao;
import com.tujuhsembilan.pointtransaction.dao.TransactionMongoDao;
import com.tujuhsembilan.pointtransaction.dao.TransactionPostgresDao;
import com.tujuhsembilan.pointtransaction.dto.ResponseDto;
import com.tujuhsembilan.pointtransaction.dto.TransactionRequest;
import com.tujuhsembilan.pointtransaction.dto.TransactionResponse;
import com.tujuhsembilan.pointtransaction.enums.TransactionStatus;
import com.tujuhsembilan.pointtransaction.helper.ResponseDtoHelper;
import com.tujuhsembilan.pointtransaction.model.BalanceMongo;
import com.tujuhsembilan.pointtransaction.model.TransactionMongo;
import com.tujuhsembilan.pointtransaction.model.TransactionPostgres;
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

    public TransactionMongo saveTransaction(TransactionRequest transactionRequest) {

        BalanceMongo currentBalance = balanceMongoDao.findByAccountId(transactionRequest.getAccountId())
                .orElse(BalanceMongo
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
                TransactionMongo.builder()
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
        List<TransactionMongo> transactions = transactionMongoDao.findAll();
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
