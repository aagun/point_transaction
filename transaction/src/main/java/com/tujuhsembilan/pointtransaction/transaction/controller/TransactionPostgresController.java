package com.tujuhsembilan.pointtransaction.transaction.controller;

import com.tujuhsembilan.pointtransaction.clients.dto.ReportRequest;
import com.tujuhsembilan.pointtransaction.clients.dto.ResponseDto;
import com.tujuhsembilan.pointtransaction.clients.helper.ResponseDtoHelper;
import com.tujuhsembilan.pointtransaction.clients.model.pg.Transaction;
import com.tujuhsembilan.pointtransaction.transaction.service.TransactionPostgresService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/pg/transactions")
@AllArgsConstructor
@Slf4j
public class TransactionPostgresController {

    private final TransactionPostgresService transactionPostgresService;

    @GetMapping
    public ResponseDto getAllTransactions() {
        List<Transaction> transactions = transactionPostgresService.getAllTransactions();
        return ResponseDtoHelper.ok(transactions);
    }

    @PostMapping
    public ResponseDto getAllTransactionsForReport(@RequestBody ReportRequest reportRequest) {
        List<Transaction> transactions = transactionPostgresService.getAllTransactionsForReport(reportRequest);
        return ResponseDtoHelper.ok(transactions);
    }
}
