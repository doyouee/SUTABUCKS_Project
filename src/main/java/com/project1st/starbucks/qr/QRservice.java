package com.project1st.starbucks.qr;

import java.io.File;
import java.io.IOException;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;


import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;


@Service
public class QRservice {
    @Autowired QRrepository qrRepo;

    // QR코드 생성 -> 이걸 모든 menu-detail / pay / basket 등으로 // 에러 났을때 코드 추가
    public ResponseEntity<Object> makeQR() throws Exception {
        Map<String, Object> resultMap = new LinkedHashMap<String, Object>();
        // The data that the QR code will contain
        // String data = "http://localhost:9999/menu/list";
        // String data = "http://192.168.0.104:9999/menu/list";
        String data = "http://haeji.mawani.kro.kr:9999/menu/list";
        // The path where the image will get saved
        String path = "D:\\qrtest\\qrtest.jpg";
        // Encoding charset
        String charset = "UTF-8";
    
        Map<EncodeHintType, ErrorCorrectionLevel> hashMap
            = new HashMap<EncodeHintType, ErrorCorrectionLevel>();
    
        hashMap.put(EncodeHintType.ERROR_CORRECTION,
                    ErrorCorrectionLevel.L);
    
        // Create the QR code and save
        // in the specified folder
        // as a jpg file
        createQR(data, path, charset, hashMap, 200, 200);
        // System.out.println("QR Code Generated!!! ");


        resultMap.put("status", true);
        resultMap.put("message", "성공적으로 QR코드를 만들었습니다.");
        return new ResponseEntity<>(resultMap, HttpStatus.OK);
    }

    // QR코드 생성 메서드
    public static void createQR(String data, String path, String charset, Map hashMap,  int height, int width) throws WriterException, IOException {
        BitMatrix matrix = new MultiFormatWriter().encode(
        new String(data.getBytes(charset), charset),
        BarcodeFormat.QR_CODE, width, height);

        MatrixToImageWriter.writeToFile(matrix,path.substring(path.lastIndexOf('.') + 1),new File(path));
    }

}
