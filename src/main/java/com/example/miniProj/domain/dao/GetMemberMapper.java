package com.example.miniProj.domain.dao;

import com.example.miniProj.domain.Member;
import com.example.miniProj.domain.dto.CertificationFindDto;
import com.example.miniProj.domain.dto.CertificationRequestDto;
import com.example.miniProj.domain.dto.MemberResultDto;
import com.example.miniProj.domain.dto.VerificationRequestDto;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
@Mapper
public interface GetMemberMapper {
    VerificationRequestDto getResult(String certTxId);
    MemberResultDto findMember(String certTxId);
}
