package com.project1st.starbucks.rank.service;

import java.util.LinkedHashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.project1st.starbucks.admin.entity.MemberEntity;
import com.project1st.starbucks.rank.entity.MemberOrderCountEntity;
import com.project1st.starbucks.rank.repository.MemberOrderCountRepository;
import com.project1st.starbucks.rank.repository.RealRankingRepository;

import jakarta.servlet.http.HttpSession;

@Service
public class RealRankingService {
    @Autowired RealRankingRepository realRankRepo;
    @Autowired MemberOrderCountRepository memOrderCountRepo;

    public ResponseEntity<Object> storeMenuList()  {
        Map<String, Object> resultMap = new LinkedHashMap<String, Object>();
        resultMap.put("status", true);
        resultMap.put("list", realRankRepo.findAll());
        return new ResponseEntity<Object>(resultMap, HttpStatus.OK);
    }

    public ResponseEntity<Object> memberRankView(HttpSession session)  {
        Map<String, Object> resultMap = new LinkedHashMap<String, Object>();
        MemberEntity memberInfo = (MemberEntity)session.getAttribute("loginUser");        
        resultMap.put("status", true);
        resultMap.put("memberRank", memOrderCountRepo.findBySbMiSeq(memberInfo.getMiSeq()));
        return new ResponseEntity<Object>(resultMap, HttpStatus.OK);
    }
}
