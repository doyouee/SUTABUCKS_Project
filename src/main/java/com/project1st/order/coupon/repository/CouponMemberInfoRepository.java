package com.project1st.order.coupon.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.project1st.order.coupon.entity.CouponMemberInfoEntity;

@Repository
public interface CouponMemberInfoRepository extends JpaRepository<CouponMemberInfoEntity, Long> {
    
}
