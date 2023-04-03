package com.example.miniProj.service;

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

    CertificationRequestDto findCertification(String certTxId);

    CertificationResponseDto postHttpClientByLogin(CertificationRequestDto certificationRequestDto,
              String _url);
    VerifyResultResponseDto postHttpClientByRegister(VerificationRequestDto verificationRequestDto,
                                                            String _url);
}
