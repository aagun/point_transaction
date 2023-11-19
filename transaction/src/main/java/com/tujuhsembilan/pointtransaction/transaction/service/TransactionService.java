package com.tujuhsembilan.pointtransaction.transaction.service;

import com.tujuhsembilan.pointtransaction.clients.dto.ResponseDto;
import com.tujuhsembilan.pointtransaction.clients.helper.ResponseDtoHelper;
import com.tujuhsembilan.pointtransaction.transaction.dto.TransactionResponse;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Stream;

@Service
@AllArgsConstructor
public class TransactionService {

    private final TransactionMongoService transactionMongoService;

    private final TransactionPostgresService transactionPostgresService;

    public ResponseDto getAllTransactions() {
        List<TransactionResponse> transactions = Stream.concat(
                transactionPostgresService.getAllMappedTransactions().stream(),
                transactionMongoService.getAllMappedTransactions().stream()
        ).toList();

        return ResponseDtoHelper.ok(transactions);
    }
}
