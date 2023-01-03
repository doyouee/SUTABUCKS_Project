package com.parkmawani.starbucksproject.controller;

import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.parkmawani.starbucksproject.entity.MemberEntity;
import com.parkmawani.starbucksproject.repository.EventDetailRepository;
import com.parkmawani.starbucksproject.repository.EventRepository;
import com.parkmawani.starbucksproject.repository.MemberRepository;
import com.parkmawani.starbucksproject.service.EventService;

import io.micrometer.common.lang.Nullable;


@RestController
@RequestMapping("/admin")
public class AdminController {
    @Autowired MemberRepository mRepo;
    @GetMapping("/list") // 접근경로
    public String getMain(Model model) {
        model.addAttribute("name", "parkmawani");
        List<MemberEntity> list = mRepo.findAll();
        model.addAttribute("list", list);  
        // templates/index.html
        return "list";
    }

    @Autowired EventService eService;
    @Autowired EventDetailRepository dRepo;
    @Autowired EventRepository eRepo;
    
    @PutMapping("/event")
    public Map<String, Object> addEvent(
    @RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") Date evStartDate 
    ,@RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") Date evEndDate 
    ,@RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") Date ediStartDate 
    ,@RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") Date ediEndDate 
    ,@RequestParam @Nullable String evContent
    ,@RequestParam @Nullable String ediContents
    ,@RequestPart @Nullable MultipartFile evFile
    ,@RequestPart MultipartFile edFile
    ) {
        Map<String, Object> map = new LinkedHashMap<>();
        eService.addEvent(evStartDate, evEndDate, ediStartDate, ediEndDate, evContent, ediContents, evFile, edFile);
        map.put("status", true);
        map.put("message", "이벤트가 등록되었습니다.");
        return map;
    }
}

