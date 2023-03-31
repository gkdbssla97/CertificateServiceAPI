package com.example.miniProj.domain.dto;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
public class VerificationRequestDto {

    private String companyCd;
    private String reqTxId;
    private String certTxId;
    private String phoneNo;
    private String userNm;
}
