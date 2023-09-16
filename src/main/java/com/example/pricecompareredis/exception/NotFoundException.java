package com.example.pricecompareredis.exception;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@EqualsAndHashCode(callSuper = true)
@Data
@ResponseStatus(code = HttpStatus.NOT_FOUND, reason = "No keys in Redis")
public class NotFoundException extends RuntimeException {

    private String errMsg;

    private HttpStatus httpStatus;

    public NotFoundException(String errMsg, HttpStatus httpStatus) {
        this.errMsg = errMsg;
        this.httpStatus = httpStatus;
    }

}
