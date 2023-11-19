package com.tujuhsembilan.pointtransaction.transaction.controller;

import com.tujuhsembilan.pointtransaction.clients.dto.ResponseDto;
import com.tujuhsembilan.pointtransaction.clients.helper.ResponseDtoHelper;
import com.tujuhsembilan.pointtransaction.transaction.dto.TransactionRequest;
import com.tujuhsembilan.pointtransaction.transaction.model.mongo.Transaction;
import com.tujuhsembilan.pointtransaction.transaction.service.TransactionMongoService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/mdb/transactions")
@AllArgsConstructor
@Slf4j
public class TransactionMongoController {

    private final TransactionMongoService transactionMongoService;

    @PostMapping
    public ResponseDto saveTransaction(@RequestBody TransactionRequest transactionRequest) {
        Transaction transaction = transactionMongoService.saveTransaction(transactionRequest);
        if (transaction.isEmpty()) {
            return ResponseDtoHelper.fail();
        }

        return ResponseDtoHelper.ok();
    }

}
