package com.tujuhsembilan.pointtransaction.helper;

import com.tujuhsembilan.pointtransaction.dto.ResponseDto;
import com.tujuhsembilan.pointtransaction.enums.ResponseStatusCode;
import java.util.Collections;
import java.util.List;

public class ResponseDtoHelper {

    public static ResponseDto ok() {
        return ok(Collections.emptyList());
    }


    public static ResponseDto ok(List list) {
        return ok("Success", list);
    }

    public static ResponseDto ok(String message, List list) {
        return new ResponseDto<>(ResponseStatusCode.SUCCESS.getValue(), message, list);
    }

    public static ResponseDto fail(String message) {
        return new ResponseDto<>(ResponseStatusCode.FAILED.getValue(), message, Collections.emptyList());
    }

    public static ResponseDto fail() {
        return fail("Failed");
    }
}
