package com.example.miniProj.domain.dto;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
public class MemberResultDto {

    private String reqTxId;
    private String telcoTxId;
    private String certTxId;
    private String resultTycd;
    private String digitalSign;
    private String ci;
    private String userNm;
    private String birthday;
    private String gender;

    //복호화 필드 값
    private String decryptedUserNm;
    private String decryptedBirthday;
    private String decryptedGender;
    private String decryptedCI;
}
