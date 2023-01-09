package com.starbucks.order.menu.service;

import java.util.LinkedHashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import com.starbucks.order.menu.repository.MenuBasicInfoRepository;

@Service
public class MenuService {
    @Autowired MenuBasicInfoRepository mRepo;
    
    // <전체 메뉴 조회하기>
    public ResponseEntity<Object> menuList(Pageable pageable) {
        Map<String, Object> resultMap = new LinkedHashMap<String, Object>();
        resultMap.put("list", mRepo.findAll());
        return new ResponseEntity<>(resultMap, HttpStatus.OK);
    }

    // <특정 메뉴 조회하기>
    public ResponseEntity<Object> munuDetailList(@RequestParam Integer mbiSeq) {
        Map<String, Object> resultMap = new LinkedHashMap<String, Object>();
        resultMap.put("detail", mRepo.findByMbiSeq(mbiSeq));
        return new ResponseEntity<>(resultMap, HttpStatus.CREATED);
    }

    
    
}
