package com.tujuhsembilan.pointtransaction.report.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.tujuhsembilan.pointtransaction.clients.client.TransactionClient;
import com.tujuhsembilan.pointtransaction.clients.dto.ResponseDto;
import com.tujuhsembilan.pointtransaction.clients.enums.TransactionStatus;
import com.tujuhsembilan.pointtransaction.clients.helper.ResponseDtoHelper;
import com.tujuhsembilan.pointtransaction.clients.model.pg.Transaction;
import com.tujuhsembilan.pointtransaction.report.dto.ReportResponse;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.format.DateTimeFormatter;
import java.util.List;

@Slf4j
@Service
@AllArgsConstructor
public class ReportService {

    private ObjectMapper mapper;

    private TransactionClient transactionClient;

    public ResponseDto getReport(com.tujuhsembilan.pointtransaction.clients.dto.ReportRequest reportRequest) {
//        ResponseDto responseDto = getDataReports(reportRequest);
        ResponseDto responseDto = transactionClient.getAllTransactionsForReport(reportRequest);
        List reports = responseDto.getData().stream().map(this::responseMapper).toList();
        return ResponseDtoHelper.ok(reports);
    }

    private ReportResponse responseMapper(Object trx) {
        Transaction transaction = mapper.convertValue(trx, Transaction.class);
        ReportResponse reportResponse = ReportResponse.builder()
                .transactionDate(transaction.getTransactionDate().format(DateTimeFormatter.ofPattern("yyyy/MM/dd")))
                .description(transaction.getDescription())
                .balance(String.valueOf(transaction.getBalance()))
                .build();

        boolean isCredit = TransactionStatus.CREDIT.getValue().equals(transaction.getDebitCreditStatus());
        reportResponse.setCredit(isCredit ? String.valueOf(transaction.getAmount()) : "-");
        reportResponse.setDebit(isCredit ? "-" : String.valueOf(transaction.getAmount()));

        return reportResponse;
    }

//    private ResponseDto getDataReports(ReportRequest reportRequest) {
//        Map<String, String> requestBody = new LinkedHashMap<>();
//        requestBody.put("accountId", String.valueOf(reportRequest.getAccountId()));
//        requestBody.put("startDate", reportRequest.getStartDate().format(DateTimeFormatter.ofPattern("yyyy-MM-dd")));
//        requestBody.put("endDate", reportRequest.getEndDate().format(DateTimeFormatter.ofPattern("yyyy-MM-dd")));
//
//        Mono<ResponseDto> responseMono = WebClient.create().post()
//                .uri("http://localhost:8082/api/v1/pg/transactions")
//                .contentType(MediaType.APPLICATION_JSON)
//                .bodyValue(requestBody)
//                .retrieve()
//                .onStatus(HttpStatusCode::isError, response ->
//                        Mono.error(new RuntimeException("Error: " + response.statusCode())))
//                .bodyToMono(ResponseDto.class);
//
//        ResponseDto responseDto = responseMono.blockOptional().orElse(ResponseDto.builder().build());
//        ResponseDto responseDto = transactionClient.getAllTransactionsForReport(reportRequest);
//        return responseDto;
//    }
}
