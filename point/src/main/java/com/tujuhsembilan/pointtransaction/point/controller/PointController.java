package com.tujuhsembilan.pointtransaction.point.controller;

import com.tujuhsembilan.pointtransaction.clients.dto.PointRequest;
import com.tujuhsembilan.pointtransaction.clients.dto.ResponseDto;
import com.tujuhsembilan.pointtransaction.point.service.PointService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/points")
@AllArgsConstructor
@Slf4j
public class PointController {

    private final PointService pointService;

    @GetMapping
    public ResponseDto getCustomerPoints() {
        return pointService.getCustomersPoint();
    }

    @PutMapping
    public ResponseDto postCustomerPoints(@RequestBody PointRequest pointRequest) {
        return pointService.postPoint(pointRequest);
    }

}
