package com.starbucks.final_project01.store.vo;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class StoreMenuVO {
    private StoreInfoVO store;
    private List<MenuInfoVO> menus;
}