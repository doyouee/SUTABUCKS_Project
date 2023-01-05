package com.parkmawani.starbucksproject.service;

import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.parkmawani.starbucksproject.entity.CouponEntity;
import com.parkmawani.starbucksproject.repository.CouponRepository;

@Service
public class CouponService {
    @Autowired CouponRepository cRepo;
    public void addCoupon(
        Long ciDiscount,
        LocalDate ciRegDt,
        LocalDate ciExDt,
        String ciName,
        String ciExplain,
        Long ciStock,
        String ciCode
        ) {
            CouponEntity coupon = CouponEntity.builder()
                .ciDiscount(ciDiscount)
                .ciRegDt(ciRegDt)
                .ciExDt(ciExDt)
                .ciName(ciName)
                .ciExplain(ciExplain)
                .ciStock(ciStock)
                .ciCode(ciCode).build();
                coupon = cRepo.save(coupon);
        }
}
