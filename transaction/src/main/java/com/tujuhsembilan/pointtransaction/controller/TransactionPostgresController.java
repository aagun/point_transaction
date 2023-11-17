package com.tujuhsembilan.pointtransaction.controller;

import com.tujuhsembilan.pointtransaction.dto.ReportRequest;
import com.tujuhsembilan.pointtransaction.dto.ResponseDto;
import com.tujuhsembilan.pointtransaction.helper.ResponseDtoHelper;
import com.tujuhsembilan.pointtransaction.model.TransactionPostgres;
import com.tujuhsembilan.pointtransaction.service.TransactionPostgresService;
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
        List<TransactionPostgres> transactions = transactionPostgresService.getAllTransactions();
        return ResponseDtoHelper.ok(transactions);
    }

    @PostMapping
    public ResponseDto getAllTransactionsForReport(@RequestBody ReportRequest reportRequest) {
        List<TransactionPostgres> transactions = transactionPostgresService.getAllTransactionsForReport(reportRequest);
        return ResponseDtoHelper.ok(transactions);
    }
}
