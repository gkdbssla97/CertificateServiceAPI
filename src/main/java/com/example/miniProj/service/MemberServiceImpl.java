package com.example.miniProj.service;

import com.example.miniProj.domain.Member;
import com.example.miniProj.domain.dao.GetMemberMapper;
import com.example.miniProj.domain.dao.PostMemberMapper;
import com.example.miniProj.domain.dto.*;
import com.example.miniProj.util.AESCipher;
import com.example.miniProj.util.RsaDecrypt;
import com.example.miniProj.util.StageServer;
import lombok.Builder;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;
import java.util.concurrent.ThreadLocalRandom;

@Service
@RequiredArgsConstructor
@Builder
public class MemberServiceImpl implements MemberService {

    private final GetMemberMapper getMemberMapper;
    private final PostMemberMapper postMemberMapper;

    @Override
    public void joinMember(CertificationRequestDto certificationRequestDto) {
        postMemberMapper.saveMember(certificationRequestDto);
    }

    @Override
    public VerificationRequestDto getResult(String certTxId) {
        return getMemberMapper.getResult(certTxId);
    }

    @Override
    public MemberResultDto findMember(String certTxId) {
        return getMemberMapper.findMember(certTxId);
    }

    @Override
    public CertificationRequestDto setCertification(MemberDto memberDto) throws Exception {

        return CertificationRequestDto.builder()
                .companyCd(StageServer.COMPANYCD)
                .serviceTycd(StageServer.SERVICETYCD)
                .reqTitle(StageServer.REQ_TITLE)
                .reqCSPhoneNo(StageServer.REQ_CSPHONE_NO)
                .reqEndDttm(createExpirationTime())
                .signTargetTycd("4")
                .signTarget(StageServer.SIGN_TARGET)
                .reqTxId(createReqTxId())
                .isCombineAuth("N")
                .isDigitalSign("Y")
                .isNotification("Y")
                .isPASSVerify("Y")
                .isUserAgreement("N")
                .memberDto(parsingMemberDto(memberDto))
                .build();
    }

    @Override
    public MemberResultDto setMemberResult(MemberResultDto memberResultDto) throws Exception {

        AESCipher aesCipher = new AESCipher(StageServer.ENCRYPT_KEY);
        RsaDecrypt rsaDecrypt = new RsaDecrypt();
        String decryptCI = rsaDecrypt.decrypt(memberResultDto.getCi());
        System.out.println(decryptCI);

        return MemberResultDto.builder()
                .reqTxId(memberResultDto.getReqTxId())
                .telcoTxId(memberResultDto.getTelcoTxId())
                .certTxId(memberResultDto.getCertTxId())
                .resultTycd(memberResultDto.getResultTycd())
                .digitalSign(memberResultDto.getDigitalSign())
                .ci(memberResultDto.getCi())
                .userNm(memberResultDto.getUserNm())
                .birthday(memberResultDto.getBirthday())
                .gender(memberResultDto.getGender())
                .decryptedUserNm(aesCipher.decrypt(memberResultDto.getUserNm()))
                .decryptedBirthday(aesCipher.decrypt(memberResultDto.getBirthday()))
                .decryptedGender(aesCipher.decrypt(memberResultDto.getGender()))
                .decryptedCI(decryptCI)
                .build();
    }

    @Override
    public void saveCertTxId(CertificationResponseDto certificationResponseDto) {
        postMemberMapper.saveCertTxId(certificationResponseDto);
    }

    @Override
    public void saveVerifyResult(VerifyResultResponseDto verifyResultResponseDto) {
        postMemberMapper.saveVerifyResult(verifyResultResponseDto);
    }

    private MemberDto parsingMemberDto(MemberDto memberDto) throws Exception {
        memberDto.setGender(Member.initGender(memberDto.getGender(), memberDto.getBirthday()));
        memberDto.setTelcoTyCd(Member.initTelcoTycd(memberDto.getTelcoTyCd()));
        memberDto.setPhoneNo(Member.initPhoneNo(memberDto.getPhoneNo()));
        return encryptMemberDto(memberDto);
    }

    private String createExpirationTime() { //현재시간 기준 5분 이후
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = Calendar.getInstance().getTime();
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.add(Calendar.MINUTE, 5);

        return simpleDateFormat.format(cal.getTime());
    }

    private String createReqTxId() { //타임스태프로 20자리 값 요청(특수문자 사용 X)
        LocalDateTime localDateTime = LocalDateTime.now();
        String timeStamp = DateTimeFormatter.ofPattern("yyyyMMddHHmmss").format(localDateTime);

        return timeStamp + String.valueOf(generateAuthNo());
    }

    private int generateAuthNo() {
        return ThreadLocalRandom.current().nextInt(100000, 1000000);
    }

    private MemberDto encryptMemberDto(MemberDto memberDto) throws Exception {
        AESCipher aesCipher = new AESCipher(StageServer.ENCRYPT_KEY);

        return MemberDto.builder()
                .telcoTyCd(memberDto.getTelcoTyCd())
                .phoneNo(aesCipher.encrypt(memberDto.getPhoneNo()))
                .userNm(aesCipher.encrypt(memberDto.getUserNm()))
                .birthday(aesCipher.encrypt(memberDto.getBirthday()))
                .gender(aesCipher.encrypt(memberDto.getGender()))
                .build();
    }
}
