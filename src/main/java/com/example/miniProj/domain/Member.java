package com.example.miniProj.domain;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ColumnDefault;
import org.json.simple.JSONObject;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Member {
    @Id
    @GeneratedValue
    private Long id;
    @Column(name = "companyCd", length = 5)
    private String companyCd; //이용기관 코드
    @Column(name = "channelTycd", length = 2)
    private String channelTycd; //이용기관의 서비스 채널구분 -> PW
    @Column(name = "channelNm", length = 40)
    private String channelNm; //이용기관의 서비스 채널이름
    @Column(name = "agencyCd", length = 2)
    private String agencyCd; //대행사 코드
    @Column(name = "serviceTycd", length = 5)
    private String serviceTycd; //인증서 서비스 유형코드
    @Column(name = "telcoTycd", length = 1)
    private String telcoTycd; //통신사 구분 코드
    @Column(name = "phoneNo", length = 40)
    private String phoneNo; //휴대폰 번호
    @Column(name = "userNm", length = 300)
    private String userNm; //사용자이름
    @Column(length = 40)
    private String birthday; //생년월일 "YYMMDD" format
    @Column(length = 40)
    private String gender; //성별로 0~9값 가짐 AES128 or AES256
    @Column(name = "reqTitle", length = 50)
    private String reqTitle; //인증요청 알림 제목
    @Column(name = "reqContent", length = 500)
    private String reqContent; //인증요청 알림 내용
    @Column(name = "reqCSPhoneNo", length = 12)
    private String reqCSPhoneNo; //이용기관의 고객센터 연락처
    @Column(name = "reqEndDttm", length = 20)
    private String reqEndDttm; //인증요청의 유효 만료일시 "YYYY-MM-DD hh:mm:ss" format
    @Column(name = "isNotification", columnDefinition = "varchar(255) default 'Y'", length = 1)
    private String isNotification; //알림내용 통지 여부 Option -> "Y" 필수
    @Column(name = "isPASSVerify", columnDefinition = "varchar(255) default 'Y'", length = 1)
    private String isPASSVerify; //서명검증 주체 식별 값 Option -> "Y" 필수
    @Column(name = "verifyURL", length = 100)
    private String verifyURL; //null 허용
    @Column(name = "signTargetTycd", length = 1)
    private String signTargetTycd;
    @Column(name = "signTarget")
    private String signTarget; //서명대상정보
    @Column(name = "isUserAgreement", columnDefinition = "varchar(255) default 'N'", length = 1)
    private String isUserAgreement; //Default -> "N"

//    private JSONObject originalInfo;

    @Column(name = "reqTxId", length = 20)
    private String reqTxId; //반드시 20자리의 값(특수문자 사용불가)
    @Column(name = "isDigitalSign", columnDefinition = "varchar(255) default 'Y'", length = 1)
    private String isDigitalSign; //-> "Y"
    @Column(name = "isCombineAuth", columnDefinition = "varchar(255) default 'N'", length = 1)
    private String isCombineAuth; //-> "N"
    @Column(name = "certTxId", length = 20)
    private String certTxId;

    // 검증 결과 요청
    @Column(name = "telcoTxId",length = 20)
    private String telcoTxId;
    @Column(name = "resultTycd", length = 1)
    private String resultTycd; //서명대상정보
    @Column(name = "resultDttm", length = 20)
    private String resultDttm; //서명대상정보
    @Column(name = "digitalSign")
    private String digitalSign; //서명대상정보
    @Column(name = "ci")
    private String ci; //서명대상정보


    //gender prop 설정
    public static String initGender(String gender, String birthday) {
        int year = Integer.parseInt("19" + birthday.substring(0, 2));

        if (gender.equals("M") && 1900 <= year && year <= 1999) {
            return "1";
        } else if (gender.equals("F") && 1900 <= year && year <= 1999) {
            return "2";
        } else if (gender.equals("M") && 2000 <= year && year <= 2099) {
            return "3";
        } else if (gender.equals("F") && 2000 <= year && year <= 2099) {
            return "4";
        }
        return "0";
    }

    public static String initTelcoTycd(String telcoTycd) {
        return telcoTycd.substring(0, 1);
    }

    public static String initPhoneNo(String phoneNo) {
        return "010"+ phoneNo;
    }
}
