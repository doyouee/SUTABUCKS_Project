package com.parkmawani.starbucksproject.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.parkmawani.starbucksproject.entity.MenuEntity;
import com.parkmawani.starbucksproject.repository.MenuRepository;

@Service
public class MenuService {
    @Autowired MenuRepository mRepo;
    public void addMenu(
        String mbiName,
        Integer mbiCost,
        String mbiExplain,
        Long mbiPcSeq
        ) {
            MenuEntity menu = MenuEntity.builder()
                .mbiName(mbiName)
                .mbiCost(mbiCost)
                .mbiExplain(mbiExplain)
                .mbiPcSeq(mbiPcSeq).build();
                menu = mRepo.save(menu);
        }
}
