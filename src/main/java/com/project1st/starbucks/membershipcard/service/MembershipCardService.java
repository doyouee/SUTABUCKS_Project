package com.project1st.starbucks.membershipcard.service;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.project1st.starbucks.admin.entity.MemberEntity;
import com.project1st.starbucks.membershipcard.entity.MembershipCardEntity;
import com.project1st.starbucks.membershipcard.repository.MembershipCardRepository;
import com.project1st.starbucks.membershipcard.vo.MembershipCardVO;

import jakarta.servlet.http.HttpSession;

@Service
public class MembershipCardService {
    @Autowired MembershipCardRepository cardRepo;

    //카드생성 -> (이미 있다면 못만들도록)
    public ResponseEntity<Object> createNewMembershipCard(MembershipCardEntity data, HttpSession session) {
        Map<String, Object> resultMap = new LinkedHashMap<String, Object>();
        // 세션으로 로그인 정보 불러오기
        MemberEntity memberInfo = (MemberEntity) session.getAttribute("loginUser");
        if (memberInfo == null) {
            resultMap.put("status", false);
            resultMap.put("message", "멤버십 카드 생성을 위해 로그인 해주세요.");
            return new ResponseEntity<>(resultMap, HttpStatus.BAD_REQUEST);
        }
        // 로그인했다면 멤버십카드
        data.setCardMiSeq(memberInfo.getMiSeq());
        cardRepo.save(data);
        resultMap.put("status", true);
        resultMap.put("message", "카드 등록이 완료되었습니다.");
        return new ResponseEntity<>(resultMap, HttpStatus.OK);
    }
    

    //카드충전 (QR코드) -> vo써야하나? // 카드가 없다면 에러
    public ResponseEntity<Object> chargeMembershipCard(Integer money, HttpSession session){
        Map<String, Object> resultMap = new LinkedHashMap<String, Object>();
        // 세션으로 로그인 정보 불러오기
        MemberEntity memberInfo = (MemberEntity) session.getAttribute("loginUser");
        if (memberInfo == null) {
            resultMap.put("status", false);
            resultMap.put("message", "멤버십 카드 충전을 위해 로그인 해주세요.");
            return new ResponseEntity<>(resultMap, HttpStatus.BAD_REQUEST);
        }

        
        MembershipCardEntity cardEntity = cardRepo.findByCardMiSeq(memberInfo.getMiSeq());
        // Optional<MembershipCardEntity> cardEntity = cardRepo.findById(card.getCardMiSeq());
        
        // 로그인+멤버십카드 소유하지 않을 때
        // if(cardRepo.findByCardMiSeq(memberInfo.getMiSeq()) == null) {
        // if(cardEntity == null) {
        //     resultMap.put("status", false);
        //     resultMap.put("message", "현재 회원님이 소유한 멤버십 카드가 없습니다.");
        //     return new ResponseEntity<>(resultMap, HttpStatus.BAD_REQUEST);
        // }
        // else {
            // 로그인+멤버십카드 있을 때
            cardEntity.setCardMoney(cardEntity.getCardMoney() + money);
            cardRepo.save(cardEntity);
            resultMap.put("status", true);
            resultMap.put("message", money + "원이 충전되었습니다. [카드 잔액 : " + cardEntity.getCardMoney() +"원]");
            return new ResponseEntity<>(resultMap, HttpStatus.OK);
        // }
    }
    
    
    //카드조회 -> 완료 ♥ // 카드가 없다면 에러
    public ResponseEntity<Object> showMembershipCard(HttpSession session){
        Map<String, Object> resultMap = new LinkedHashMap<String, Object>();
        // 세션으로 로그인 정보 불러오기
        MemberEntity memberInfo = (MemberEntity) session.getAttribute("loginUser");
        if (memberInfo == null) {
            resultMap.put("status", false);
            resultMap.put("message", "멤버십 카드 조회를 위해 로그인 해주세요.");
            return new ResponseEntity<>(resultMap, HttpStatus.BAD_REQUEST);
        }
        // 로그인했다면 멤버십카드 조회하기
        MembershipCardVO cardVO = new MembershipCardVO(cardRepo.findByCardMiSeq(memberInfo.getMiSeq()));
        resultMap.put("status", true);
        resultMap.put("detail", cardVO);
        resultMap.put("message", memberInfo.getMiName() + " [닉네임:"+ memberInfo.getMiNickname() +"] 님의 멤버십 카드 상세조회 입니다.");
        return new ResponseEntity<>(resultMap, HttpStatus.OK);
    }


    //카드삭제 -> deleteVO 쓰는 방법? // 카드가 없다면 에러
    public ResponseEntity<Object> deleteMembershipCard(HttpSession session){
        Map<String, Object> resultMap = new LinkedHashMap<String, Object>();
        // 세션으로 로그인 정보 불러오기
        MemberEntity memberInfo = (MemberEntity) session.getAttribute("loginUser");
        if (memberInfo == null) {
            resultMap.put("status", false);
            resultMap.put("message", "멤버십 카드 삭제를 위해 로그인 해주세요.");
            return new ResponseEntity<>(resultMap, HttpStatus.BAD_REQUEST);
        }
        // 로그인했다면 멤버십카드 삭제하기
        MembershipCardEntity cardEntity = cardRepo.findByCardMiSeq(memberInfo.getMiSeq());
        cardRepo.delete(cardEntity);
        resultMap.put("status", true);
        resultMap.put("message", "멤버십 카드가 삭제되었습니다.");
        return new ResponseEntity<>(resultMap, HttpStatus.OK);
    }

    //카드결제

    
}
