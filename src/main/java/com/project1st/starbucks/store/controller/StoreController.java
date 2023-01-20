package com.project1st.starbucks.store.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.project1st.starbucks.store.service.StoreService;
import com.project1st.starbucks.store.vo.StoreEditVO;
import com.project1st.starbucks.store.vo.StoreMenuAddVO;

import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/store")
public class StoreController {
    @Autowired StoreService stService;

    @GetMapping("/mystore") // <내 지점 조회하기> -> 완료 ♥
    public ResponseEntity<Object> getMyStoreInfo(HttpSession session) {
        return stService.myStoreInfo(session);
    }


    @PatchMapping("/mystore") // <내 지점 수정하기> -> 완료 ♥
    public ResponseEntity<Object> patchMyStoreEdit(@RequestBody StoreEditVO data, HttpSession session) {
        return stService.myStoreEdit(data, session);
    }


    @GetMapping("/menu/search") // <내 지점에 메뉴 검색하기> -> 완료 ♥
    public ResponseEntity<Object> getStoreMenuSearch(@RequestParam String menuName, HttpSession session) {
        return stService.storeMenuSearch(menuName, session);
    }


    @GetMapping("/menu/list") // <내 지점에 등록된 메뉴 보여주기> -> 완료 ♥
    public ResponseEntity<Object> getStoreMenuList(/*@RequestParam Long storeSeq,*/Pageable pageable, HttpSession session) {
        return stService.storeMenuList(pageable, session);
    }


    @GetMapping("/menu/list/detail") // <내 지점에 등록된 메뉴 상세보기> -> 완료 ♥
    public ResponseEntity<Object> getStoreMenuDetail(HttpSession session, @RequestParam Long menuSeq) {
        return stService.storeMenuDetail(session, menuSeq);
    }


    @PutMapping("/menu/add") // <내 지점에 메뉴 등록하기> -> 완료 ♥
    // public ResponseEntity<Object> putStoreMenuList(@RequestBody StoreMenuAddVO data, HttpSession session) {
    public ResponseEntity<Object> putStoreMenuList(@RequestParam Long menuSeq, HttpSession session) {
        return stService.insertStoreMenuList(menuSeq, session);
    }

    
    @DeleteMapping("/menu/delete") // <내 지점에 메뉴 삭제하기> -> 완료 ♥
    public ResponseEntity< Map<String, Object> > deleteStoreMenuList(@RequestParam Long menuSeq, HttpSession session) {
        return stService.deleteStoreMenuList(menuSeq, session);
    }


    @GetMapping("/search") // <가게 검색하기> -> 완료 ♥
    public ResponseEntity<Object> getStoreSearch(@RequestParam String branchName) {
        return stService.searchStoreBranchName(branchName);
    }
}
