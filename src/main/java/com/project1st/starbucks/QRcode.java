// package com.project1st.starbucks;

// import java.io.File;
// import java.util.Hashtable;

// import javax.imageio.ImageIO;
// import java.awt.image.BufferedImage;

// import com.google.zxing.BarcodeFormat;
// import com.google.zxing.EncodeHintType;
// import com.google.zxing.Writer;
// import com.google.zxing.WriterException;
// import com.google.zxing.client.j2se.MatrixToImageConfig;
// import com.google.zxing.client.j2se.MatrixToImageWriter;
// import com.google.zxing.qrcode.QRCodeWriter;
// import com.google.zxing.qrcode.encoder.ByteMatrix;

// public class QRcode {
//     public static void main(String[] args) throws Exception{

//         // QRCodeWriter writer = new QRCodeWriter();
//         // try {
//         //     Hashtable hints = new Hashtable();     
//         //     hints.put(EncodeHintType.CHARACTER_SET, "UTF-8");
//         //     ByteMatrix matrix = Writer.encode("한글입력", BarcodeFormat.QR_CODE, 230, 230, hints);
//         //     MatrixToImageWriter.writeToFile(matrix , "png", new File("d:/test1.png"));
//         // } 
//         // catch (WriterException e) {
//         //     e.printStackTrace();
//         // }



//         // try {
        
//         //     File file = null;
//         //     // 큐알이미지를 저장할 디렉토리 지정
//         //     file = new File("D:\\qrtest");
//         //     if(!file.exists()) {
//         //         file.mkdirs();
//         //     }
//         //     // 코드인식시 링크걸 URL주소
//         //     String codeurl = new String("http://hellogk.tistory.com".getBytes("UTF-8"), "ISO-8859-1");
//         //     // 큐알코드 바코드 생상값
//         //     int qrcodeColor =   0xFF2e4e96;
//         //     // 큐알코드 배경색상값
//         //     int backgroundColor = 0xFFFFFFFF;
            
//         //     QRCodeWriter qrCodeWriter = new QRCodeWriter();
//         //     // 3,4번째 parameter값 : width/height값 지정
//         //     BitMatrix bitMatrix = qrCodeWriter.encode(codeurl, BarcodeFormat.QR_CODE,200, 200);
//         //     // 
//         //     MatrixToImageConfig matrixToImageConfig = new MatrixToImageConfig(qrcodeColor,backgroundColor);
//         //     BufferedImage bufferedImage = MatrixToImageWriter.toBufferedImage(bitMatrix,matrixToImageConfig);
//         //     // ImageIO를 사용한 바코드 파일쓰기
//         //     ImageIO.write(bufferedImage, "png", new File("D:\\qrtest\\qrcode.png"));
            
//         // } 
//         // catch (Exception e) {
//         //     e.printStackTrace();
//         // }   
    
//     }    
// }
