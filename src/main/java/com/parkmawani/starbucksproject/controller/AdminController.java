package com.parkmawani.starbucksproject.controller;

import java.time.LocalDate;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.parkmawani.starbucksproject.entity.EventDetailEntity;
import com.parkmawani.starbucksproject.entity.EventEntity;
import com.parkmawani.starbucksproject.entity.MemberEntity;
import com.parkmawani.starbucksproject.repository.AnnouncementRepository;
import com.parkmawani.starbucksproject.repository.EventDetailRepository;
import com.parkmawani.starbucksproject.repository.EventRepository;
import com.parkmawani.starbucksproject.repository.MemberRepository;
import com.parkmawani.starbucksproject.repository.MenuImageRepository;
import com.parkmawani.starbucksproject.repository.MenuRepository;
import com.parkmawani.starbucksproject.repository.StoreRepository;
import com.parkmawani.starbucksproject.service.AnnouncementService;
import com.parkmawani.starbucksproject.service.CouponService;
import com.parkmawani.starbucksproject.service.EventService;
import com.parkmawani.starbucksproject.service.MenuImageService;
import com.parkmawani.starbucksproject.service.MenuService;
import com.parkmawani.starbucksproject.service.StoreService;

import io.micrometer.common.lang.Nullable;



@RestController
@RequestMapping("/admin")
public class AdminController {
    @Autowired EventService eService;
    @Autowired EventDetailRepository dRepo;
    @Autowired EventRepository eRepo;
    @Autowired MemberRepository mRepo;
    @Autowired AnnouncementService aService;
    @Autowired AnnouncementRepository aRepo;
    @Autowired MenuRepository meRepo;
    @Autowired MenuService meService;
    @Autowired StoreService sService;
    @Autowired StoreRepository sRepo;
    @Autowired MenuImageService meiService;
    @Autowired CouponService cService;

    @GetMapping("/list") // 접근경로
    public Map<String, Object> getMain(Model model) {
        Map<String, Object> resultMap = new LinkedHashMap<String, Object>();
        List<MemberEntity> list = mRepo.findAll();
        resultMap.put("member", list);  
        // templates/index.html
        return resultMap;
    }

    
    @GetMapping("/event")
    public Map<String, Object> getEvent(Model model) {
        Map<String, Object> eventMap = new LinkedHashMap<String, Object>();
        List<EventEntity> event = eRepo.findAll();

        Map<String, Object> detailMap = new LinkedHashMap<String, Object>();
        List<EventDetailEntity> detail = dRepo.findAll();

        eventMap.put("event", event);
        detailMap.put("detail", detail);
        return eventMap;
        
    }

    @PostMapping("/event")
    public Map<String, Object> addEvent(
    @RequestParam  @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate evStartDate 
    ,@RequestParam  @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate evEndDate 
    ,@RequestParam  @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate ediStartDate 
    ,@RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd")  LocalDate ediEndDate 
    ,@RequestParam @Nullable String evContent
    ,@RequestParam @Nullable String ediContents
    ,@RequestPart MultipartFile evFile
    ,@RequestPart MultipartFile edFile
    ) {
        Map<String, Object> map = new LinkedHashMap<>();
        eService.addEvent(evStartDate, evEndDate, ediStartDate, ediEndDate, evContent, ediContents, evFile, edFile);
        map.put("status", true);
        map.put("message", "이벤트가 등록되었습니다.");
        return map;
    }

    @PostMapping("/notice")
    public Map<String, Object> addAnnouncement(
        @RequestParam String saTitle,
        @RequestParam String saContent,
        @RequestPart MultipartFile saImgFile
    ) {
        Map<String, Object> map = new LinkedHashMap<>();
        aService.addEvent(saTitle, saContent, saImgFile);
        map.put("status", true);
        map.put("message", "공지사항이 등록되었습니다.");
        return map;
    }

    @GetMapping("/modify")
    public Map<String, Object> getMemberStatusUpdate(@RequestParam Long seq, @RequestParam Integer status) {
        MemberEntity entity = mRepo.findByMiSeq(seq);
        entity.setMiStatus(status);
        mRepo.save(entity);
        Map<String, Object> map = new LinkedHashMap<>();
        map.put("status", true);
        map.put("message", "유저의 상태를 변경하였습니다.");
        return map;
    }

    @PostMapping("menu")
    public Map<String, Object> addMenu(
        @RequestParam String mbiName,
        @RequestParam Integer mbiCost,
        @RequestParam String mbiExplain,
        @RequestParam Long mbiPcSeq
    ) {
        Map<String, Object> map = new LinkedHashMap<>();
        meService.addMenu(mbiName, mbiCost, mbiExplain,mbiPcSeq);
        map.put("status", true);
        map.put("message", "메뉴가 등록되었습니다.");
        return map;
    }

    @PostMapping("menuimg")
    public Map<String, Object> addMenuImage(
        @RequestParam Long miiNumber,
        @RequestPart MultipartFile miiImgFile
    ){
        Map<String, Object> map = new LinkedHashMap<>();
        meiService.addEvent(miiImgFile, miiNumber);
        map.put("status", true);
        map.put("message", "메뉴 이미지가 등록되었습니다.");
        return map;
    }

    @PostMapping("store")
    public Map<String, Object> addStore (
        @RequestParam String sbiBranchName,
        @RequestParam String sbiAddressBasic,
        @RequestParam String sbiAddressDetail,
        @RequestParam String sbiOpenTime,
        @RequestParam String sbiCloseTime,
        @RequestParam String sbiCloseDay,
        @RequestParam Long sbiMinOrder,
        @RequestParam String sbiCeoName,
        @RequestParam String sbiBusinessAddress,
        @RequestParam String sbiPhone,
        @RequestParam String sbiMinDeliveryTime,
        @RequestParam String sbiMaxDeliveryTime,
        @RequestParam Long sbiMiSeq
    ){
        Map<String, Object> map = new LinkedHashMap<>();
        sService.addStore(sbiBranchName, sbiAddressBasic, sbiAddressDetail, sbiOpenTime, sbiCloseTime, sbiCloseDay, sbiMinOrder, sbiCeoName, sbiBusinessAddress, sbiPhone, sbiMinDeliveryTime, sbiMaxDeliveryTime, sbiMiSeq);
        map.put("status", true);
        map.put("message", "지점이 등록되었습니다.");
        return map;
    }


    @PostMapping("/coupon")
    public Map<String, Object> addCoupon (
        @RequestParam Long ciDiscount,
        @RequestParam LocalDate ciRegDt,
        @RequestParam LocalDate ciExDt,
        @RequestParam String ciName,
        @RequestParam String ciExplain,
        @RequestParam Long ciStock,
        @RequestParam String ciCode
    ){
        Map<String, Object> map = new LinkedHashMap<>();
        cService.addCoupon(ciDiscount, ciRegDt, ciExDt, ciName, ciExplain, ciStock, ciCode);
        map.put("status", true);
        map.put("message", "쿠폰이 등록되었습니다.");
        return map;
    }
}
    


