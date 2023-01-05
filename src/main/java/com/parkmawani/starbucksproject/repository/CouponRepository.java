package com.parkmawani.starbucksproject.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.parkmawani.starbucksproject.entity.CouponEntity;

public interface CouponRepository extends JpaRepository<CouponEntity, Long>{
    
}
