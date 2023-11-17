package com.tujuhsembilan.pointtransaction.service;

import com.tujuhsembilan.pointtransaction.dto.ResponseDto;
import com.tujuhsembilan.pointtransaction.dto.TransactionResponse;
import com.tujuhsembilan.pointtransaction.helper.ResponseDtoHelper;
import com.tujuhsembilan.pointtransaction.model.TransactionPostgres;
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
