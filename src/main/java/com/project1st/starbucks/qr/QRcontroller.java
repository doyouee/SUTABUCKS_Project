package com.project1st.starbucks.qr;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import jakarta.servlet.http.HttpServletRequest;


@RestController
@RequestMapping("/QR")
public class QRcontroller{
    @Autowired QRservice Qservice;
    
    // QR코드 생성 -> 이걸 모든 menu-detail / pay / basket 등으로 // 에러 났을때 코드 추가
    @PostMapping("/new")
    public ResponseEntity<Object> postQR(@RequestParam String menuName) throws Exception{
        return Qservice.makeQR(menuName);
    }


    // 만든 QR코드 이미지 다운로드
    @GetMapping("/image/{uri}")
    public ResponseEntity<Resource> getMenuQRImage(@PathVariable String uri, HttpServletRequest request) throws Exception {
        return Qservice.getMenuQRImage(uri, request);
    }


}
