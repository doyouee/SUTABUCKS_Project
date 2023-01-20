package com.project1st.starbucks.coupon.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.project1st.starbucks.coupon.entity.CouponInfoEntity;



@Repository
public interface CouponInfoRepository extends JpaRepository<CouponInfoEntity, Long> {
    
}
