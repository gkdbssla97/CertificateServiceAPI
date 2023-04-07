package com.example.miniProj.controller;

import com.example.miniProj.domain.dto.*;
import com.example.miniProj.service.MemberService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import static com.example.miniProj.util.Constants.*;

@Controller
@RequiredArgsConstructor
@Slf4j
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

//        return memberService.postHttpClientByLogin(certificationRequestDto, certificationUrl);
        return memberService.postHttpClientByLogin(certificationRequestDto, certificationTestUrl, FIRST_REGISTER);
    }

    @PostMapping(value = "/reRequestRegister")
    @ResponseBody
    public CertificationResponseDto reRequestCertificate(@RequestBody String certTxId) throws Exception {

        CertificationRequestDto reRegisterCertification = memberService.findCertification(certTxId);
        System.out.println(reRegisterCertification);
        memberService.resetReqEndDttm(reRegisterCertification);

//        return memberService.postHttpClientByLogin(reRegisterCertification, certificationUrl);
        return memberService.postHttpClientByLogin(reRegisterCertification, certificationTestUrl, RE_REGISTER);
    }

    @PostMapping(value = "/joinRegister")
    @ResponseBody
    public VerifyResultResponseDto joinCertificate(@RequestBody String certTxId) throws Exception {
        VerificationRequestDto verificationRequestDto = memberService.getResult(certTxId);
        System.out.println("Controller Layer: " + verificationRequestDto);

//        return memberService.postHttpClientByRegister(verificationRequestDto, verificationUrl);
        return memberService.postHttpClientByRegister(verificationRequestDto, verificationTestUrl);
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
}
