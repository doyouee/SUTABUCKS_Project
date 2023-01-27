package com.project1st.starbucks.qr;

import java.io.File;
import java.io.IOException;
import java.net.URLEncoder;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.Calendar;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;
import com.project1st.starbucks.menu.entity.MenuQrEntity;
import com.project1st.starbucks.menu.repository.MenuBasicInfoRepository;
import com.project1st.starbucks.menu.repository.MenuQrRepository;

import jakarta.servlet.http.HttpServletRequest;


@Service
public class QRservice {
    @Autowired QRrepository qrRepo;
    @Autowired MenuQrRepository menuQrRepo;
    @Autowired MenuBasicInfoRepository menuRepo;
    @Value("${file.image.menuqr}") String qr_menu_img_path;



    // 메뉴의 QR코드 생성 -> 메뉴의 qr코드 생성
    public ResponseEntity<Object> makeQR(String menuName) throws Exception {
        Map<String, Object> resultMap = new LinkedHashMap<String, Object>();
        Long menuNo = menuRepo.findByMbiName(menuName).getMbiSeq();
        // String data = "http://www.naver.com";
        // String data = "http://localhost:9999/menu/list";
        // String data = "http://192.168.0.104:9999/menu/list";
        String data = "http://haeji.mawani.kro.kr:9999/menu/list/detail?menuNo=" + menuNo;
        String path = "D:\\home\\starbucks\\image\\menuqr\\" + menuName + ".jpg";
        String charset = "UTF-8";
        Map<EncodeHintType, ErrorCorrectionLevel> hashMap = new HashMap<EncodeHintType, ErrorCorrectionLevel>();
        hashMap.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.L);
        createQR(data, path, charset, hashMap, 200, 200);

        MenuQrEntity qrdata = MenuQrEntity.builder()
            .mqiImageFile(menuName + ".jpg")
            .mqiUri(menuName)
            .mqiMbiSeq(menuNo).build();
        qrdata = menuQrRepo.save(qrdata);

        
        resultMap.put("status", true);
        resultMap.put("message", "성공적으로 QR코드를 만들었습니다.");
        return new ResponseEntity<>(resultMap, HttpStatus.OK);
    }


    // 메뉴의 QR코드 생성 메서드
    public static void createQR(String data, String path, String charset, Map hashMap,  int height, int width) throws WriterException, IOException {
        BitMatrix matrix = new MultiFormatWriter().encode(
            new String(data.getBytes(charset), charset), BarcodeFormat.QR_CODE, width, height);
        MatrixToImageWriter.writeToFile(matrix,path.substring(path.lastIndexOf('.') + 1),new File(path));
    }


    // 메뉴의 QR이미지 다운로드 메서드
    public ResponseEntity<Resource> getMenuQRImage (@PathVariable String uri, HttpServletRequest request) throws Exception {
        String filename = null;
        Path folderLocation = null;
        
        filename = getFilenameByUri(uri);
        folderLocation = Paths.get(qr_menu_img_path);

        String[] split = filename.split("\\.");
        String ext = split[split.length - 1];
        String exportName = uri + "." + ext; // 내보내줄 파일이름은 다르다. (내보낼 파일의 이름을 만든다.)                                                                                                                                                                                                                                                                  

        Path targetFile = folderLocation.resolve(filename); //폴더 경로와 파일의 이름을 합쳐서 목표 파일의 경로를 만든다.
        Resource r = null; //파일을 가져와서 resource라는 형태로 바꿔서 내보내줘야한다. ( 다운로드 가능한 형태로 변환하기 위한 Resurce 객체 생성 )
        try {
            r = new UrlResource(targetFile.toUri());
        } catch (Exception e) { e.printStackTrace(); }
        String contentType = null;
        try {
            contentType = request.getServletContext().getMimeType(r.getFile().getAbsolutePath());
            if(contentType == null) {
                contentType = "application/octet-stream";
            }
        } catch (Exception e) { e.printStackTrace(); }
        return ResponseEntity.ok()
        .contentType(MediaType.parseMediaType(contentType))
        .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename*=\""+ URLEncoder.encode(exportName, "UTF-8") + "\"")
        .body(r);
    }

    
    // 파일명 가져오기
    public String getFilenameByUri(String uri) {
        // List<MembershipCardQREntity> data = cardQRRepo.findTopByCardqrUriOrderByCardqrSeqDesc(uri);
        return (menuQrRepo.findTopByMqiUriOrderByMqiSeqDesc(uri)).getMqiImageFile();
        // return data.get(0).getCardqrUri();
    }


}

