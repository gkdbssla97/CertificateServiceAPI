package com.example.miniProj.util.error;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ErrorResponseEntity {
    private int errorCode;
    private String message;
    private String errorPointCd;
    private HttpStatus httpStatus;
    private String telcoTxId;
    private String reqTxId;
    private String certTxId;

    public ErrorResponseEntity(int errorCode, String message, String errorPointCd, HttpStatus httpStatus) {
        this.errorCode = errorCode;
        this.message = message;
        this.errorPointCd = errorPointCd;
        this.httpStatus = httpStatus;
    }
}
