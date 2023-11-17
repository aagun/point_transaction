package com.tujuhsembilan.pointtransaction.service;

import com.tujuhsembilan.pointtransaction.dto.ReportRequest;
import com.tujuhsembilan.pointtransaction.dto.ReportResponse;
import com.tujuhsembilan.pointtransaction.dto.ResponseDto;
import com.tujuhsembilan.pointtransaction.enums.TransactionStatus;
import com.tujuhsembilan.pointtransaction.helper.ResponseDtoHelper;
import com.tujuhsembilan.pointtransaction.model.Transaction;
import java.util.LinkedHashMap;
import java.util.Map;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
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

    public ResponseDto getReport(ReportRequest reportRequest) {

        log.info(">>>>>>>>>>>>>>>>>>> reportRequest {}", reportRequest.toString());
//
//        HttpHeaders headers = new HttpHeaders();
//        headers.setContentType(MediaType.APPLICATION_JSON);

        Map<String, String> requestBody = new LinkedHashMap<>();
        requestBody.put("accountId", String.valueOf(reportRequest.getAccountId()));
        requestBody.put("startDate", reportRequest.getStartDate().format(DateTimeFormatter.ofPattern("yyyy-MM-dd")));
        requestBody.put("endDate", reportRequest.getEndDate().format(DateTimeFormatter.ofPattern("yyyy-MM-dd")));

//        HttpEntity<Map<String, String>> responseEntity = new HttpEntity<>(requestBody, headers);
//        RestTemplate restTemplate = new RestTemplate();
//
//        ResponseDto responsesss = restTemplate.exchange(
//            "http://localhost:8082/api/v1/pg/transactions",
//            HttpMethod.POST,
//            responseEntity,
//            ResponseDto.class
//        ).getBody();
//
//        log.info(">>>>>>>>>>>>>>>>>>> result {}", responsesss.toString());
//

        Mono<ResponseDto> responseMono = WebClient.create().post()
                .uri("http://localhost:8082/api/v1/pg/transactions")
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(requestBody)
                .retrieve()
                .onStatus(HttpStatusCode::isError, response ->
                        Mono.error(new RuntimeException("Error: " + response.statusCode())))
                .bodyToMono(ResponseDto.class);

        ResponseDto responseDto = responseMono.blockOptional().orElse(ResponseDto.builder().build());

        
        List reports = responseDto.getData().stream().map(trx -> {

            log.info(">>>>>>>>>> trx {}", trx.toString());
            Transaction transaction = ((Transaction) trx);

            ReportResponse reportResponse = ReportResponse.builder()
                    .transactionDate(transaction.getTransactionDate().format(DateTimeFormatter.ofPattern("yyyy/MM/dd")))
                    .description(transaction.getDescription())
                    .balance(String.valueOf(transaction.getBalance()))
                    .build();

            boolean isCredit = TransactionStatus.CREDIT.getValue().equals(transaction.getDebitCreditStatus());
            reportResponse.setCredit(isCredit ? String.valueOf(transaction.getAmount()) : "-");
            reportResponse.setDebit(isCredit ? "-" : String.valueOf(transaction.getAmount()));

            return reportResponse;
        }).toList();

        return ResponseDtoHelper.ok(reports);
    }
}
