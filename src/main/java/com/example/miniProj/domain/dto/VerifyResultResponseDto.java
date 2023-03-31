package com.example.miniProj.domain.dto;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
public class VerifyResultResponseDto {
    private String resultTycd;
    private String resultDttm;
    private String digitalSign;
    private String ci;
    private String telcoTxId;
    private String reqTxId;
}
