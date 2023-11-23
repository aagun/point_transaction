package com.tujuhsembilan.pointtransaction.point.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.tujuhsembilan.pointtransaction.clients.client.CustomerClient;
import com.tujuhsembilan.pointtransaction.clients.dto.PointRequest;
import com.tujuhsembilan.pointtransaction.clients.dto.ResponseDto;
import com.tujuhsembilan.pointtransaction.clients.enums.ResponseStatusCode;
import com.tujuhsembilan.pointtransaction.clients.enums.TransactionDesc;
import com.tujuhsembilan.pointtransaction.clients.enums.TransactionStatus;
import com.tujuhsembilan.pointtransaction.clients.helper.ResponseDtoHelper;
import com.tujuhsembilan.pointtransaction.clients.model.pg.Customer;
import com.tujuhsembilan.pointtransaction.point.dao.PointDao;
import com.tujuhsembilan.pointtransaction.point.model.Point;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Objects;

@AllArgsConstructor
@Service
@Slf4j
@Transactional
public class PointService {

    private final PointDao pointDao;

    private final CustomerClient customerClient;

    private final ObjectMapper mapper;

    private static Long creditPoint(Double amount) {
        var points = 0L;

        if (amount > 100_000D) {
            points += (long) (((amount - 100_000D) / 2_000) * 2);
            amount = 100_000D;
        }

        if (amount > 50_000D) {
            points += (long) (((amount - 50_000D) / 2_000) * 1);
        }

        return points;
    }

    private static Long electricityPoint(Double amount) {
        var points = 0L;

        if (amount > 30_000D) {
            points += (long) (((amount - 30_000D) / 2_000) * 2);
            amount = 30_000D;
        }

        if (amount > 10_000D) {
            points += (long) (((amount - 10_000D) / 2_000) * 1);
        }

        return points;
    }

    private static Long calculatePoint(Double amount, TransactionDesc desc) {
        var points = 0L;

        switch (desc) {
            case BELI_PULSA -> points = creditPoint(amount);
            case BAYAR_LISTRIK -> points = electricityPoint(amount);
        }

        return points;
    }

    public ResponseDto postPoint(PointRequest pointRequest) {
        var customerResponse = customerClient.getCustomer(String.valueOf(pointRequest.getAccountId()));

        if (Objects.equals(ResponseStatusCode.FAILED.getValue(), customerResponse.getStatus())) {
            return ResponseDtoHelper.fail(String.format("Customer with account id [%s] not found", pointRequest.getAccountId()));
        }

        var customer =  mapper.convertValue(customerResponse.getData().get(0), Customer.class);
        var point = pointDao.findByCustomerAccountId(pointRequest.getAccountId())
                .orElse(Point.builder().customer(customer).points(0L).build());
        var points = calculatePoint(pointRequest.getAmount(), pointRequest.getTransactionDescription());

        log.info("{}", customer.toString());
        log.info("{}", point.toString());
        log.info("points {}", points);

        point.setPoints(point.getPoints() + points);
        point.setCustomer(customer);

        pointDao.save(point);

        return ResponseDtoHelper.ok(Collections.singletonList(point));
    }

    public ResponseDto getCustomersPoint() {
        List<Point> points = pointDao.findAll();
        return ResponseDtoHelper.ok(points);
    }
}
