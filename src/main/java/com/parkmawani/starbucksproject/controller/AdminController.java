package com.parkmawani.starbucksproject.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.parkmawani.starbucksproject.entity.MemberEntity;
import com.parkmawani.starbucksproject.repository.MemberRepository;


@Controller
@RequestMapping("/admin")
public class AdminController {
    @Autowired MemberRepository repo;
    @GetMapping("/list") // 접근경로
    public String getMain(Model model) {
        model.addAttribute("name", "parkmawani");
        List<MemberEntity> list = repo.findAll();
        model.addAttribute("list", list);  
        // templates/index.html
        return "list";
    }
}
