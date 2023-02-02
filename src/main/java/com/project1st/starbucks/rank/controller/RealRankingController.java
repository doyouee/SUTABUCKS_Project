package com.project1st.starbucks.rank.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.project1st.starbucks.admin.service.session;
import com.project1st.starbucks.rank.service.RealRankingService;

import jakarta.servlet.http.HttpSession;

@RestController
public class RealRankingController {
    @Autowired RealRankingService realRankingService;
    @GetMapping("/menu/rank")
    public ResponseEntity<Object> menuRanking() {
        return realRankingService.storeMenuList();
    }

    @GetMapping("/member/rank")
    public ResponseEntity<Object> memberRanking(HttpSession session) {
        return realRankingService.memberRankView(session);
    }
}
