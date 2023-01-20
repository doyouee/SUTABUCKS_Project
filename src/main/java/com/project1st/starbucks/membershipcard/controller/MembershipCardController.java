package com.project1st.starbucks.membershipcard.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.project1st.starbucks.membershipcard.entity.MembershipCardEntity;
import com.project1st.starbucks.membershipcard.service.MembershipCardService;

import jakarta.servlet.http.HttpSession;

@RestController
@RequestMapping("/card")
public class MembershipCardController {
    @Autowired MembershipCardService cardService;

    //카드생성 -> 이미 만들어져 있을시 에러
    @PostMapping("/new")
    public ResponseEntity<Object> postNewMembershipCard(@RequestBody MembershipCardEntity data, HttpSession session) {
        return cardService.createNewMembershipCard(data, session);
    }


    //카드충전 (QR코드)
    @PatchMapping("/charge")
    public ResponseEntity<Object> patchChargeMembershipCard(@RequestParam Integer money, HttpSession session) {
        return cardService.chargeMembershipCard(money, session);
    }
    

    //카드조회 -> 완료 ♥
    @GetMapping("/detail")
    public ResponseEntity<Object> getMembershipCard(HttpSession session) {
        return cardService.showMembershipCard(session);
    }
    

    //카드삭제 -> 잔액있을 시 에러
    @DeleteMapping("/delete")
    public ResponseEntity<Object> deleteMembershipCard(HttpSession session) {
        return cardService.deleteMembershipCard(session);
    }


    //카드결제
}
