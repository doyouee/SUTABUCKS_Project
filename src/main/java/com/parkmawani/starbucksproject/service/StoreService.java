package com.parkmawani.starbucksproject.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.parkmawani.starbucksproject.entity.StoreEntity;
import com.parkmawani.starbucksproject.repository.StoreRepository;

import io.micrometer.common.lang.Nullable;

@Service
public class StoreService {
    @Autowired  StoreRepository sRepo;
        public void addStore (
            String sbiBranchName,
            String sbiAddressBasic,
            String sbiAddressDetail,
            @Nullable String sbiOpenTime,
            @Nullable String sbiCloseTime,
            String sbiCloseDay,
            @Nullable Long sbiMinOrder,
            String sbiCeoName,
            String sbiBusinessAddress,
            String sbiPhone,
            @Nullable String sbiMinDeliveryTime,
            @Nullable String sbiMaxDeliveryTime,
            Long sbiMiSeq
        ) {
            StoreEntity store = StoreEntity.builder()
                .sbiBranchName(sbiBranchName)
                .sbiAddressBasic(sbiAddressBasic)
                .sbiAddressDetail(sbiAddressDetail)
                .sbiOpenTime(sbiOpenTime)
                .sbiCloseTime(sbiCloseTime)
                .sbiCloseDay(sbiCloseDay)
                .sbiMinOrder(sbiMinOrder)
                .sbiCeoName(sbiCeoName)
                .sbiBusinessAddress(sbiBusinessAddress)
                .sbiPhone(sbiPhone)
                .sbiMinDeliveryTime(sbiMinDeliveryTime)
                .sbiMaxDeliveryTime(sbiMaxDeliveryTime)
                .sbiMiSeq(sbiMiSeq).build();
                store = sRepo.save(store);
        }
}
