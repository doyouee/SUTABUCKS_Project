package com.project1st.starbucks.admin.service;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import com.project1st.starbucks.admin.entity.StoreEntity;
import com.project1st.starbucks.admin.repository.StoreRepository;
import com.project1st.starbucks.menu.entity.MenuBasicInfoEntity;
import com.project1st.starbucks.menu.repository.MenuBasicInfoRepository;
import com.project1st.starbucks.menu.vo.MenuInfoVO;
import com.project1st.starbucks.store.entity.StoreBasicInfoEntity;
import com.project1st.starbucks.store.entity.StoreMenuConnectEntity;
import com.project1st.starbucks.store.repository.StoreBasicInfoRepository;
import com.project1st.starbucks.store.repository.StoreMenuConnectRepository;
import com.project1st.starbucks.store.vo.StoreInfoVO;
import com.project1st.starbucks.store.vo.StoreMenuAddVO;
import com.project1st.starbucks.store.vo.StoreMenuVO;

import io.micrometer.common.lang.Nullable;

@Service
public class StoreAdminService {
    @Autowired StoreRepository sRepo;
    @Autowired StoreBasicInfoRepository sbiRepo; 
    @Autowired MenuBasicInfoRepository mRepo;
    @Autowired StoreMenuConnectRepository smRepo;
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
                .sbiMaxDeliveryTime(sbiMaxDeliveryTime).build();
                store = sRepo.save(store);
        }

}
