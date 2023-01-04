package com.parkmawani.starbucksproject.controller;

import java.time.LocalDate;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.parkmawani.starbucksproject.entity.MemberEntity;
import com.parkmawani.starbucksproject.repository.AnnouncementRepository;
import com.parkmawani.starbucksproject.repository.EventDetailRepository;
import com.parkmawani.starbucksproject.repository.EventRepository;
import com.parkmawani.starbucksproject.repository.MemberRepository;
import com.parkmawani.starbucksproject.service.AnnouncementService;
import com.parkmawani.starbucksproject.service.EventService;

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

    @GetMapping("/list") // 접근경로
    public Map<String, Object> getMain(Model model) {
        Map<String, Object> resultMap = new LinkedHashMap<String, Object>();
        resultMap.put("name", "parkmawani");
        List<MemberEntity> list = mRepo.findAll();
        resultMap.put("list", list);  
        // templates/index.html
        return resultMap;
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

    @PostMapping("/modify")
    public Map<String, Object> modifyMemberStatus(
        @RequestParam Long miSeq,
        @RequestParam Integer miStatus
    ) {
        // slelct
        Map<String, Object> map = new LinkedHashMap<>();
        map.put("stauts", true);
        map.put("message", "회원 상태가 변경되었습니다.");
        return map;
    }

}

