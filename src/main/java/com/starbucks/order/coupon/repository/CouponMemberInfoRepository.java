package com.starbucks.order.coupon.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.starbucks.order.coupon.entity.CouponMemberInfoEntity;

@Repository
public interface CouponMemberInfoRepository extends JpaRepository<CouponMemberInfoEntity, Long> {
    
}
