package com.project1st.starbucks.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.project1st.starbucks.entity.MemberInfoEntity;



public interface MemberInfoRepository extends JpaRepository <MemberInfoEntity, Long>  {
    // 아이디 중복 검사
    Integer countBymiId(String miId);
    // 닉네임 중복 검사
    Integer countBymiNickname(String miNickName);
    // 휴대폰 번호 중복 검사
    Integer countBymiPhoneNum(String miPhoneNum);

    MemberInfoEntity findByMiIdAndMiPwd(String miId, String miPwd);
    // MemberInfoEntity findByUserIdAndUserPwd(String id, String pwd);

    // 현재 로그인한 사용자 아이디와 일치하는 사용자 정보 출력 ( 로그인 사용자 정보 조회 )
    MemberInfoEntity findByMiId(String miId);
    
    // 로그인한 회원 miSeq에 해당하는 정보 출력 
    MemberInfoEntity findByMiSeq(Long miSeq);
}