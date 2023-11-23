package com.tujuhsembilan.pointtransaction.customer.service;

import com.tujuhsembilan.pointtransaction.clients.dto.ResponseDto;
import com.tujuhsembilan.pointtransaction.clients.helper.ResponseDtoHelper;
import com.tujuhsembilan.pointtransaction.clients.model.pg.Customer;
import com.tujuhsembilan.pointtransaction.customer.dao.CustomerDao;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@Service
@Slf4j
public class CustomerService {

    private final CustomerDao customerDao;

    public ResponseDto getCustomer(Long accountId) {
        Optional<Customer> customer = customerDao.findByAccountId(accountId);
        return customer.map(value -> ResponseDtoHelper.ok(Collections.singletonList(value)))
                .orElseGet(ResponseDtoHelper::fail);
    }

    public ResponseDto getCustomers() {
        List<Customer> customers = customerDao.findAll();
        return ResponseDtoHelper.ok(customers);
    }

    public ResponseDto postCustomer(Customer customerRequest) {
        if (customerDao.existsByAccountId(customerRequest.getAccountId())) {
            return ResponseDtoHelper.fail(String.format("Customer with id %s already exists", customerRequest.getAccountId()));
        }

        Customer newCustomer = customerDao.save(Customer.builder()
                .accountId(customerRequest.getAccountId())
                .name(customerRequest.getName())
                .build()
        );

        return ResponseDtoHelper.ok(Collections.singletonList(newCustomer));
    }

    public ResponseDto putCustomer(Customer customerRequest) {
        Optional<Customer> customer = customerDao.findByAccountId(customerRequest.getAccountId());
        if (!customer.isPresent()) {
            return ResponseDtoHelper.fail(String.format("Customer with account id [%s] not found", customerRequest.getAccountId()));
        }

        customerRequest.setId(customer.get().getId());
        customerDao.save(customerRequest);
        return ResponseDtoHelper.ok();
    }
}
