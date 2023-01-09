package com.starbucks.final_project01.coupon.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.starbucks.final_project01.coupon.entity.CouponMemberInfoEntity;

@Repository
public interface CouponMemberInfoRepository extends JpaRepository<CouponMemberInfoEntity, Long> {
    
}
