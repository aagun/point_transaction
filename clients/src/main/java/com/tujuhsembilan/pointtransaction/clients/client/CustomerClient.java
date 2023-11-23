package com.tujuhsembilan.pointtransaction.clients.client;

import com.tujuhsembilan.pointtransaction.clients.dto.ResponseDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name="customer", url = "${clients.customer.url}")
public interface CustomerClient {
    @GetMapping("/api/v1/customers/{accountId}")
    ResponseDto getCustomer(@PathVariable("accountId") String accountId);
}
