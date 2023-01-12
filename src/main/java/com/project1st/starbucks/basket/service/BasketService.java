package com.project1st.starbucks.basket.service;

import java.util.LinkedHashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.project1st.starbucks.basket.entity.ShoppingBasketEntity;
import com.project1st.starbucks.basket.repository.ShoppingBasketOptionRepository;
import com.project1st.starbucks.basket.repository.ShoppingBasketRepository;
import com.project1st.starbucks.basket.vo.DetailBasketVO;
import com.project1st.starbucks.store.repository.StoreMenuConnectRepository;

@Service
public class BasketService {
    @Autowired ShoppingBasketRepository sbRepo;
    @Autowired ShoppingBasketOptionRepository sbopRepo;
    @Autowired StoreMenuConnectRepository smcRepo;

    public ResponseEntity<Object> addDetailBasket(DetailBasketVO data) {
        Map<String, Object> resultMap = new LinkedHashMap<String, Object>();
        long i = 1;
        ShoppingBasketEntity entity = ShoppingBasketEntity.builder().sbMiSeq(data.getSbMiSeq()).sbStatus(i)
                                                .storeMenuConnect(smcRepo.findById(data.getSbSmcSeq()).get()).build();
        sbRepo.save(entity);
        resultMap.put("result", true);
        resultMap.put("entity", entity);
        return new ResponseEntity<>(resultMap, HttpStatus.OK);
    }
}
