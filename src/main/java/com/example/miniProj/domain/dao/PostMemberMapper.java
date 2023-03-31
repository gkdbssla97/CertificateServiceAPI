package com.example.miniProj.domain.dao;

import com.example.miniProj.domain.dto.CertificationRequestDto;
import com.example.miniProj.domain.dto.CertificationResponseDto;
import com.example.miniProj.domain.dto.VerifyResultResponseDto;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
@Mapper
public interface PostMemberMapper {
    void saveMember(@Param("certificationRequestDto") CertificationRequestDto certificationRequestDto);
    void saveCertTxId(@Param("certificationResponseDto") CertificationResponseDto certificationResponseDto);
    void saveVerifyResult(@Param("verifyResultResponseDto") VerifyResultResponseDto verifyResultResponseDto);
}
