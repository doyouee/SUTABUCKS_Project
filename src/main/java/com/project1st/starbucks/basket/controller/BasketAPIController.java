package com.project1st.starbucks.basket.controller;

import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.project1st.starbucks.basket.entity.ShoppingBasketEntity;
import com.project1st.starbucks.basket.repository.ShoppingBasketRepository;
import com.project1st.starbucks.basket.service.BasketService;
import com.project1st.starbucks.basket.vo.DetailBasketVO;


@RestController

public class BasketAPIController {
    @Autowired ShoppingBasketRepository sbRepo;        
    @GetMapping("/testa")    
     public Map<String, Object> basket()  {        
        Map<String, Object> resultMap = new LinkedHashMap<String, Object>();
        resultMap.put("list", sbRepo.findAll());
        return resultMap;
     }

     @GetMapping("/testb")
     public Map<String, Object> memberBasket(@RequestParam Long memSeq, @RequestParam Long status) {
         Map<String, Object> resultMap = new LinkedHashMap<String, Object>();
         resultMap.put("memberBasket", sbRepo.findBySbMiSeqAndSbStatus(memSeq, status));
         return resultMap;
     }
 
    @PatchMapping("/testb") // 주문하는 메소드
    public Map<String, Object> updateOrder(@RequestParam Long memSeq, @RequestParam Long status, @RequestParam Long change, @RequestBody ShoppingBasketEntity shopBasket) {
        Map<String, Object> resultMap = new LinkedHashMap<String, Object>();        
        List<ShoppingBasketEntity> changeBasket = sbRepo.findBySbMiSeqAndSbStatus(memSeq, status);
        for(ShoppingBasketEntity basket : changeBasket) {
            Date now = new Date();
            basket.setSborderDate(now);
            basket.setSbStatus(change);  
            basket.setSbReceive(shopBasket.getSbReceive());
            basket.setSbRequest(shopBasket.getSbRequest());
            basket.setSbPayment(shopBasket.getSbPayment());            
            sbRepo.save(basket);            
        }
        resultMap.put("message", "장바구니 상태값 변경");
        return resultMap;
    }
    

    @Autowired BasketService basketService;
    @PutMapping("/testc") // 디테일 페이지에서 담는 메소드 
    public ResponseEntity<Object> getDetailBasket(@RequestBody DetailBasketVO data) {        
        return basketService.addDetailBasket(data);
    }
}
