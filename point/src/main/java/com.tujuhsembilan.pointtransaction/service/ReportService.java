package com.tujuhsembilan.pointtransaction.service;

import com.tujuhsembilan.pointtransaction.dao.TransactionDao;
import com.tujuhsembilan.pointtransaction.dto.ReportRequest;
import com.tujuhsembilan.pointtransaction.dto.ReportResponse;
import com.tujuhsembilan.pointtransaction.dto.ResponseDto;
import com.tujuhsembilan.pointtransaction.enums.TransactionStatus;
import com.tujuhsembilan.pointtransaction.helper.ResponseDtoHelper;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;

@Service
@AllArgsConstructor
public class ReportService {

    private final TransactionDao transactionDao;

    public ResponseDto getReport(ReportRequest reportRequest) {
        AtomicReference<List<ReportResponse>> reports = new AtomicReference<>(new ArrayList<>(0));

        transactionDao.findByCustomer_AccountIdAndTransactionDateBetween(
                reportRequest.getAccountId(),
                reportRequest.getStartDate(),
                reportRequest.getEndDate()
        ).ifPresent(transactions -> reports.set(transactions.stream().map(transaction -> {
            ReportResponse reportResponse = ReportResponse.builder()
                    .transactionDate(transaction.getTransactionDate().format(DateTimeFormatter.ofPattern("yyyy/MM/dd")))
                    .description(transaction.getDescription())
                    .balance(String.valueOf(transaction.getBalance()))
                    .build();

            boolean isCredit = TransactionStatus.CREDIT.getValue().equals(transaction.getDebitCreditStatus());
            reportResponse.setCredit(isCredit ? String.valueOf(transaction.getAmount()) : "-");
            reportResponse.setDebit(isCredit ? "-" : String.valueOf(transaction.getAmount()));

            return reportResponse;
        }).toList()));

        return ResponseDtoHelper.ok(reports.get().stream().toList());
    }
}
