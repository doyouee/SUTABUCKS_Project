package com.starbucks.final_project01.member.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.starbucks.final_project01.member.entity.MemberInfoEntity;

@Repository
public interface MemberInfoRepository extends JpaRepository<MemberInfoEntity, Long> {
    
}
