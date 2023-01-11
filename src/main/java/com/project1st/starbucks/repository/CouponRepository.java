package com.project1st.starbucks.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.project1st.starbucks.entity.CouponEntity;



public interface CouponRepository extends JpaRepository<CouponEntity, Long>{
    
}
