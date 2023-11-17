package com.tujuhsembilan.pointtransaction.dto;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.tujuhsembilan.pointtransaction.helper.LocalDateToLocalDateTimeDeserializer;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ReportRequest {

    private Long accountId;

    @JsonDeserialize(using = LocalDateToLocalDateTimeDeserializer.class)
    private LocalDate startDate;

    @JsonDeserialize(using = LocalDateToLocalDateTimeDeserializer.class)
    private LocalDate endDate;
}
