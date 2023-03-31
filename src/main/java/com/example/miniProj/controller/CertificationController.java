package com.example.miniProj.controller;

import com.example.miniProj.domain.Member;
import com.example.miniProj.domain.dto.*;
import com.example.miniProj.service.MemberService;
import com.example.miniProj.util.StageServer;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.MapperFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;

@Controller
@RequiredArgsConstructor
public class CertificationController {

    private final MemberService memberService;

    @RequestMapping("join")
    public String home() {
        return "join";
    }

    @RequestMapping("/login")
    public String login() {
        return "login";
    }

    @RequestMapping("/result")
    public String result() {
        return "result";
    }

    @PostMapping(value = "/loginRegister")
    @ResponseBody
    public CertificationResponseDto loginCertificate(@RequestBody MemberDto memberDto) throws Exception {

        CertificationRequestDto certificationRequestDto = memberService.setCertification(memberDto);
        memberService.joinMember(certificationRequestDto);

        System.out.println(memberDto);
        System.out.println(certificationRequestDto);

        String certificationUrl = "https://api-stg.passauth.co.kr/v1/certification/notice";

        return postHttpUrlConnectionByLogin(certificationRequestDto, certificationUrl);
    }

    @PostMapping(value = "/joinRegister")
    @ResponseBody
    public String joinCertificate(@RequestBody String certTxId) throws Exception {

        System.out.println("certTxId: " + certTxId);
        VerificationRequestDto verificationRequestDto = memberService.getResult(certTxId);
        String verificationUrl = "https://api-stg.passauth.co.kr/v1/certification/result";
        postHttpUrlConnectionByRegister(verificationRequestDto, verificationUrl);

        return "";
    }

    @PostMapping(value = "/resultRegister")
    @ResponseBody
    public MemberResultDto resultCertificate(@RequestBody String certTxId) throws Exception {

        System.out.println("certTxId: " + certTxId);
        MemberResultDto findMember = memberService.findMember(certTxId);
        MemberResultDto memberResultDto = memberService.setMemberResult(findMember);

        System.out.println(memberResultDto);

        return memberResultDto;
    }

    private CertificationResponseDto postHttpUrlConnectionByLogin(CertificationRequestDto certificationRequestDto,
                                       String _url) {

        JSONObject jsonObject = getJsonObjectByLogin(certificationRequestDto);

        try {
            HttpURLConnection httpUrlConnection = null;

            URL url = new URL(_url);
            httpUrlConnection = (HttpURLConnection) url.openConnection();

            httpUrlConnection.setRequestMethod("POST");

            httpUrlConnection.setRequestProperty("Content-Type", "application/json;charset=UTF-8");
            httpUrlConnection.setRequestProperty("Authorization", "Bearer " + StageServer.ACCESS_TOKEN);

            //POST-> String JSON 전송
            httpUrlConnection.setDoOutput(true);
            BufferedWriter bufferedWriter = new BufferedWriter(
                    new OutputStreamWriter(httpUrlConnection.getOutputStream())
            );

            bufferedWriter.write(jsonObject.toString());
            bufferedWriter.flush();
            bufferedWriter.close();

            //서버에서 보낸 응답 데이터 수신
            BufferedReader bufferedReader = new BufferedReader(
                    new InputStreamReader(httpUrlConnection.getInputStream())
            );
            String returnMsg = bufferedReader.readLine();
            System.out.println("returnMsg = " + returnMsg);

            ObjectMapper objectMapper = new ObjectMapper();
            CertificationResponseDto certificationResponseDto =
                    objectMapper.readValue(returnMsg, CertificationResponseDto.class);

            memberService.saveCertTxId(certificationResponseDto);

            return certificationResponseDto;
        } catch (IOException ie) {
            System.out.println("IOException" + ie.getCause());
            ie.printStackTrace();
        } catch (Exception e) {
            System.out.println("Exception" + e.getCause());
            e.printStackTrace();
        }
            return null;
    }

    private static JSONObject getJsonObjectByLogin(CertificationRequestDto certificationDto) {
        JSONObject jsonObject = new JSONObject();

        jsonObject.put("companyCd", certificationDto.getCompanyCd());
        jsonObject.put("serviceTycd", certificationDto.getServiceTycd());
        jsonObject.put("telcoTycd", certificationDto.getMemberDto().getTelcoTyCd());
        jsonObject.put("phoneNo", certificationDto.getMemberDto().getPhoneNo());
        jsonObject.put("userNm", certificationDto.getMemberDto().getUserNm());
        jsonObject.put("birthday", certificationDto.getMemberDto().getBirthday());
        jsonObject.put("gender", certificationDto.getMemberDto().getGender());
        jsonObject.put("reqTitle", certificationDto.getReqTitle());
        jsonObject.put("reqCSPhoneNo", certificationDto.getReqCSPhoneNo());
        jsonObject.put("reqEndDttm", certificationDto.getReqEndDttm());
        jsonObject.put("isNotification", certificationDto.getIsNotification());
        jsonObject.put("isPASSVerify", certificationDto.getIsPASSVerify());
        jsonObject.put("signTargetTycd", certificationDto.getSignTargetTycd());
        jsonObject.put("signTarget", certificationDto.getSignTarget());
        jsonObject.put("isUserAgreement", certificationDto.getIsUserAgreement());
        jsonObject.put("reqTxId", certificationDto.getReqTxId());
        jsonObject.put("isDigitalSign", certificationDto.getIsDigitalSign());

        return jsonObject;
    }


    private void postHttpUrlConnectionByRegister(VerificationRequestDto verificationRequestDto,
                                       String _url) {

        JSONObject jsonObject = getJsonObjectByRegister(verificationRequestDto);

        try {
            HttpURLConnection httpUrlConnection = null;

            URL url = new URL(_url);
            httpUrlConnection = (HttpURLConnection) url.openConnection();

            httpUrlConnection.setRequestMethod("POST");

            httpUrlConnection.setRequestProperty("Content-Type", "application/json;charset=UTF-8");
            httpUrlConnection.setRequestProperty("Authorization", "Bearer " + StageServer.ACCESS_TOKEN);

            //POST-> String JSON 전송
            httpUrlConnection.setDoOutput(true);
            BufferedWriter bufferedWriter = new BufferedWriter(
                    new OutputStreamWriter(httpUrlConnection.getOutputStream())
            );

            bufferedWriter.write(jsonObject.toString());
            bufferedWriter.flush();
            bufferedWriter.close();

            //서버에서 보낸 응답 데이터 수신
            BufferedReader bufferedReader = new BufferedReader(
                    new InputStreamReader(httpUrlConnection.getInputStream())
            );
            String returnMsg = bufferedReader.readLine();
            System.out.println("returnMsg = " + returnMsg);

            ObjectMapper objectMapper = new ObjectMapper();
            objectMapper.configure(MapperFeature.ACCEPT_CASE_INSENSITIVE_PROPERTIES, true);
            objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

            VerifyResultResponseDto verifyResultResponseDto =
                    objectMapper.readValue(returnMsg, VerifyResultResponseDto.class);
            System.out.println(verifyResultResponseDto);

            memberService.saveVerifyResult(verifyResultResponseDto);

        } catch (IOException ie) {
            System.out.println("IOException" + ie.getCause());
            ie.printStackTrace();
        } catch (Exception e) {
            System.out.println("Exception" + e.getCause());
            e.printStackTrace();
        }
    }

    private static JSONObject getJsonObjectByRegister(VerificationRequestDto verificationRequestDto) {
        JSONObject jsonObject = new JSONObject();

        jsonObject.put("companyCd", verificationRequestDto.getCompanyCd());
        jsonObject.put("reqTxId",verificationRequestDto.getReqTxId());
        jsonObject.put("certTxId", verificationRequestDto.getCertTxId());
        jsonObject.put("phoneNo",verificationRequestDto.getPhoneNo());
        jsonObject.put("userNm",verificationRequestDto.getUserNm());

        return jsonObject;
    }
}
