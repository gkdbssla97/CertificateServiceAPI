package com.example.miniProj.util;

import java.util.List;

public class Constants {
    public final static String COMPANYCD = "90001";
    public final static String ACCESS_TOKEN = "AT-111111";
    public final static String ENCRYPT_KEY = "YzNmOGQ2OGI1ZDEwNDA5YmJmZmRhMTI5";
    public final static List<String> SERVICETYCD_LIST = List.of("S1002", "S1003", "S2001", "S3001", "S3002");
    public final static String REQ_TITLE = "인증요청 알림 제목";
    public final static String REQ_CSPHONE_NO = "02-786-4273";
    public final static String SIGN_TARGET = "NONE";
    public final static String certificationUrl = "https://api-stg.passauth.co.kr/v1/certification/notice";
    public final static String certificationTestUrl = "http://localhost:8081/v1/certification/notice";
    public final static String verificationUrl = "https://api-stg.passauth.co.kr/v1/certification/result";
    public final static String verificationTestUrl = "http://localhost:8081/v1/certification/result";

    public final static String FIRST_REGISTER = "first Register";
    public final static String RE_REGISTER = "re Register";
}
