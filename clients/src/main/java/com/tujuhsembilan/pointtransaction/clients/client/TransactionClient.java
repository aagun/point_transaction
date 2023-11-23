package com.tujuhsembilan.pointtransaction.clients.client;

import com.tujuhsembilan.pointtransaction.clients.dto.ReportRequest;
import com.tujuhsembilan.pointtransaction.clients.dto.ResponseDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "transaction", url = "${clients.transaction.url}")
public interface TransactionClient {

    @PostMapping(path = "/api/v1/pg/transactions")
    ResponseDto getAllTransactionsForReport(@RequestBody ReportRequest reportRequest);
}
