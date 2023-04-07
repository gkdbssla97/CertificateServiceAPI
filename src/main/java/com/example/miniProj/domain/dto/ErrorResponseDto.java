package com.example.miniProj.domain.dto;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
public class ErrorResponseDto {
    private String errorCd;
    private String message;
    private String telcoTxId;
    private String certTxId;
    private String reqTxId;
}
