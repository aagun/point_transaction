package com.tujuhsembilan.pointtransaction.customer.controller;

import com.tujuhsembilan.pointtransaction.clients.model.pg.Customer;
import com.tujuhsembilan.pointtransaction.clients.dto.ResponseDto;
import com.tujuhsembilan.pointtransaction.clients.helper.ResponseDtoHelper;
import com.tujuhsembilan.pointtransaction.customer.service.CustomerService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/customers")
@AllArgsConstructor
@Slf4j
public class CustomerController {

    private final CustomerService customerService;

    @GetMapping("/{accountId}")
    public ResponseDto getCustomer(@PathVariable("accountId") String accountId) {
        return customerService.getCustomer(Long.valueOf(accountId));
    }

    @GetMapping
    public ResponseDto getCustomers() {
        return customerService.getCustomers();
    }

    @PostMapping
    public ResponseDto postCustomer(@RequestBody Customer customer) {
        return customerService.postCustomer(customer);
    }

    @PutMapping
    public ResponseDto putCustomer(@RequestBody Customer customer) {
        return customerService.putCustomer(customer);
    }
}
