package com.example.miniProj.domain.dto;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
public class CertificationRequestDto {
        private String companyCd; //이용기관 코드
        private String serviceTycd; //인증서 서비스 유형코드
        private String reqTitle; //인증요청 알림 제목
        private String reqCSPhoneNo; //이용기관의 고객센터 연락처
        private String reqEndDttm; //인증요청의 유효 만료일시 "YYYY-MM-DD hh:mm:ss" format 현재시간 + 5분
        private String signTargetTycd;
        private String signTarget; //서명대상정보
        private String reqTxId; //반드시 20자리의 값(특수문자 사용불가) 랜덤으로 응답
        private String isCombineAuth;
        private String isDigitalSign;
        private String isNotification;
        private String isPASSVerify;
        private String isUserAgreement;
        private MemberDto memberDto;
}
