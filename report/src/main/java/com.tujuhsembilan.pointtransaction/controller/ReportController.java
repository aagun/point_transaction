package com.tujuhsembilan.pointtransaction.controller;

import com.tujuhsembilan.pointtransaction.dto.ReportRequest;
import com.tujuhsembilan.pointtransaction.dto.ResponseDto;
import com.tujuhsembilan.pointtransaction.service.ReportService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/reports")
@AllArgsConstructor
@Slf4j
public class ReportController {

    private final ReportService reportService;

    @PostMapping
    public ResponseDto getReport(@RequestBody ReportRequest reportRequest) {
        return reportService.getReport(reportRequest);
    }

}
