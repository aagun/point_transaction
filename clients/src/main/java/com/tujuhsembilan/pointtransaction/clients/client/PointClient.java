package com.tujuhsembilan.pointtransaction.clients.client;

import com.tujuhsembilan.pointtransaction.clients.dto.PointRequest;
import com.tujuhsembilan.pointtransaction.clients.dto.ResponseDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "point", url = "${clients.point.url}")
public interface PointClient {

    @PostMapping("/api/v1/points")
    ResponseDto postCustomerPoints(@RequestBody PointRequest pointRequest);
}
