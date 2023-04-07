package com.example.miniProj.domain.dao;

import com.example.miniProj.domain.dto.*;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
@Mapper
public interface GetMemberMapper {
    VerificationRequestDto getResult(String certTxId);
    MemberResultDto findMember(String certTxId);
    CertificationRequestDto findCertification(String certTxId);
    ServiceTycdDto findServiceTycd(@Param("memberDto") MemberDto memberDto);
}
