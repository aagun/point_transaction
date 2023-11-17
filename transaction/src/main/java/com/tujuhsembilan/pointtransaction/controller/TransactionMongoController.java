package com.tujuhsembilan.pointtransaction.controller;

import com.tujuhsembilan.pointtransaction.dto.ResponseDto;
import com.tujuhsembilan.pointtransaction.dto.TransactionRequest;
import com.tujuhsembilan.pointtransaction.helper.ResponseDtoHelper;
import com.tujuhsembilan.pointtransaction.model.TransactionMongo;
import com.tujuhsembilan.pointtransaction.service.TransactionMongoService;
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
        TransactionMongo transaction = transactionMongoService.saveTransaction(transactionRequest);
        if (transaction.isEmpty()) {
            return ResponseDtoHelper.fail();
        }

        return ResponseDtoHelper.ok();
    }

}
