package com.project1st.starbucks.qr;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/QR")
public class QRcontroller{
    @Autowired QRservice Qservice;
    
    // QR코드 생성 -> 이걸 모든 menu-detail / pay / basket 등으로 // 에러 났을때 코드 추가
    @GetMapping("/new")
    // @RequestMapping(path="/new", method = RequestMethod.GET, produces = "application/hal+json;charset=utf8")
    public ResponseEntity<Object> postQR() throws Exception{
        return Qservice.makeQR();
    }


    // 만든 QR코드 이미지를 상세메뉴 테이블에 담기 -> 상세메뉴 테이블부터 코드까지 싹 다 고치기

    // 만든 QR코드 이미지를 결제페이지로 쓰기

    // 만든 QR코드 이미지를 멤버십 결제로도 쓰기

}
