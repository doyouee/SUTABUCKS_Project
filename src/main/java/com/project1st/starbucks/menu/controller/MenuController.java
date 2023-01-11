package com.project1st.starbucks.menu.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.project1st.starbucks.admin.service.MenuService;




@RestController
@RequestMapping("/menu")
public class MenuController {
    @Autowired MenuService mService;
    
    @GetMapping("/list") // <전체 메뉴 조회> -> 완료 ♥
    public ResponseEntity<Object> getMenuList(Pageable pageable) {
        return mService.menuList(pageable);
    }

    @GetMapping("/list/detail") // <특정 메뉴 조회> -> 완료 ♥
    public ResponseEntity<Object> getMunuDetailList(@RequestParam Long mbiSeq) {
        return mService.munuDetailList(mbiSeq);
    }

    //-------------------------------------------------------------------------------------------

    @GetMapping("/cate") // <전체 카테고리 조회>
    public ResponseEntity<Object> getCategoryList() {
        return mService.categoryList();
    }

    // @GetMapping("/cate") // <부모 카테고리 조회>
    // public ResponseEntity<Object> getCategoryParentList(@RequestParam Integer parentSeq) {
    //     return mService.categoryParentList(parentSeq);
    // }

}
