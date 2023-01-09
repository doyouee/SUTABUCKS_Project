package com.starbucks.final_project01.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.starbucks.final_project01.entity.CouponEntity;

public interface CouponRepository extends JpaRepository<CouponEntity, Long>{
    
}
