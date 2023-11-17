package com.tujuhsembilan.pointtransaction.service;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.tujuhsembilan.pointtransaction.dto.ReportRequest;
import com.tujuhsembilan.pointtransaction.dto.ReportResponse;
import com.tujuhsembilan.pointtransaction.dto.ResponseDto;
import com.tujuhsembilan.pointtransaction.enums.TransactionStatus;
import com.tujuhsembilan.pointtransaction.helper.ResponseDtoHelper;
import com.tujuhsembilan.pointtransaction.model.Transaction;
import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.Map;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.json.GsonJsonParser;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.BodyInserter;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.time.format.DateTimeFormatter;
import java.util.List;

@Slf4j
@Service
@AllArgsConstructor
public class ReportService {

    private ObjectMapper mapper;

    public ResponseDto getReport(ReportRequest reportRequest) {
        ResponseDto responseDto = getDataReports(reportRequest);
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

    private static ResponseDto getDataReports(ReportRequest reportRequest) {
        Map<String, String> requestBody = new LinkedHashMap<>();
        requestBody.put("accountId", String.valueOf(reportRequest.getAccountId()));
        requestBody.put("startDate", reportRequest.getStartDate().format(DateTimeFormatter.ofPattern("yyyy-MM-dd")));
        requestBody.put("endDate", reportRequest.getEndDate().format(DateTimeFormatter.ofPattern("yyyy-MM-dd")));

        Mono<ResponseDto> responseMono = WebClient.create().post()
                .uri("http://localhost:8082/api/v1/pg/transactions")
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(requestBody)
                .retrieve()
                .onStatus(HttpStatusCode::isError, response ->
                        Mono.error(new RuntimeException("Error: " + response.statusCode())))
                .bodyToMono(ResponseDto.class);

        ResponseDto responseDto = responseMono.blockOptional().orElse(ResponseDto.builder().build());
        return responseDto;
    }
}
