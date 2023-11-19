package com.tujuhsembilan.pointtransaction.report.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ReportResponse {

    private String transactionDate;
    private String description;
    private String credit;
    private String debit;
    private String balance;
}
