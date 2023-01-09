package com.starbucks.order.menu.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.starbucks.order.menu.service.MenuService;

@RestController
@RequestMapping("/menu")
public class MenuController {
    @Autowired MenuService mService;
    
    @GetMapping("/list") // <전체 메뉴 조회>
    public ResponseEntity<Object> getMenuList(Pageable pageable) {
        return mService.menuList(pageable);
    }

    @GetMapping("/list/{detail}") // <특정 메뉴 조회>
    public ResponseEntity<Object> getMunuDetailList(@PathVariable Integer detail) {
        return mService.munuDetailList(detail);
    }

    //-------------------------------------------------------------------------------------------



}
