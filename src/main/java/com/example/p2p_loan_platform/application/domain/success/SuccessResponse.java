package com.example.p2p_loan_platform.application.domain.success;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.http.ResponseEntity;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class SuccessResponse<T> {
    private String code;
    private String message;
    private T result;

    public SuccessResponse(SuccessCode successCode, T result) {
        this.code = successCode.getCode();
        this.message = successCode.getMessage();
        this.result = result;
    }

    public static ResponseEntity<SuccessResponse> toResponseEntity(SuccessCode successCode, Object result) {
        SuccessResponse res = new SuccessResponse(successCode.getCode(), successCode.getMessage(), result);
        return new ResponseEntity<>(res, successCode.getHttpStatus());
    }
}