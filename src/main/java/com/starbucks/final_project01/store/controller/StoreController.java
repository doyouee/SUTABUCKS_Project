package com.starbucks.final_project01.store.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.starbucks.final_project01.store.service.StoreService;
import com.starbucks.final_project01.store.vo.StoreMenuAddVO;

@Controller
@RequestMapping("/store/menu")
public class StoreController {
    @Autowired StoreService stService;

    @GetMapping("/list") // <가게에 등록된 메뉴 보여주기>
    public ResponseEntity<Object> getStoreMenuList(@RequestParam Long storeSeq, Pageable pageable) {
        return stService.storeMenuList(pageable, storeSeq);
    }

    @GetMapping("/list/detail") // <가게에 등록된 메뉴 상세보기>
    public ResponseEntity<Object> getStoreMenuDetail(@RequestParam Long storeSeq, @RequestParam Long menuSeq) {
        return stService.storeMenuDetail(storeSeq, menuSeq);
    }

    @PutMapping("/add") // <가게에 메뉴 등록하기>
    public ResponseEntity<Object> putStoreMenuList(@RequestBody StoreMenuAddVO data) {
        return stService.insertStoreMenuList(data);
    }

    @DeleteMapping("/delete") // <가게에 메뉴 삭제하기>
    public ResponseEntity< Map<String, Object> > deleteStoreMenuList(@RequestParam Long store, @RequestParam Long menu) {
        return stService.deleteStoreMenuList(store, menu);
    }

}
