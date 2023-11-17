package com.tujuhsembilan.pointtransaction.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.tujuhsembilan.pointtransaction.helper.LocalDateToLocalDateTimeDeserializer;
import java.io.Serializable;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = false)
public class ReportRequest implements Serializable {

    private Long accountId;

    @JsonDeserialize(using = LocalDateToLocalDateTimeDeserializer.class)
    private LocalDate startDate;

    @JsonDeserialize(using = LocalDateToLocalDateTimeDeserializer.class)
    private LocalDate endDate;
}
