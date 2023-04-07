package com.example.miniProj.domain.dto;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
public class MemberDto {
    private String telcoTyCd; //통신사 구분 코드
    private String phoneNo; //휴대폰 번호
    private String userNm; //사용자이름
    private String birthday; //생년월일 "YYMMDD" format
    private String gender; //성별로 0~9값 가짐 AES128 or AES256

}
