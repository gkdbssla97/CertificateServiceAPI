package com.example.miniProj.service;

import com.example.miniProj.domain.Member;
import com.example.miniProj.domain.dto.*;

public interface MemberService {

    // POSTMapper
    void joinMember(CertificationRequestDto certificationRequestDto);
    void saveCertTxId(CertificationResponseDto certificationResponseDto) throws Exception;
    void saveVerifyResult(VerifyResultResponseDto verifyResultResponseDto);

    // parsingDtoFieldValue
    CertificationRequestDto setCertification(MemberDto memberDto) throws Exception;
    MemberResultDto setMemberResult(MemberResultDto memberResultDto) throws Exception;

    // GETMapper
    VerificationRequestDto getResult(String certTxId);
    MemberResultDto findMember(String certTxId);
}
