package com.example.miniProj.domain.dto;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
public class VerificationResponseDto {

    private String reqTxId;
    private String telcoTxId;
    private String certTxIdEncrypted;
    private String certTxIdDecrypted;
    private String resultTycd; //1.인증 완료
    private String resultDttm;
    private String digitalSign; //resultTycd -> 1.인증 완료일경우
    private String ci; //resultTycd -> 1.인증 완료일경우, serviceTycd: S3001일 경우
    private String userNm; //resultTycd -> 1.인증 완료일경우
    private String birthday; //resultTycd -> 1.인증 완료일경우
    private String gender; //resultTycd -> 1.인증 완료일경우
    private String phoneNo; //resultTycd -> 1.인증 완료일경우, serviceTycd: 해당 X,
    private String telcoTycd; //resultTycd -> 1.인증 완료일경우
}
