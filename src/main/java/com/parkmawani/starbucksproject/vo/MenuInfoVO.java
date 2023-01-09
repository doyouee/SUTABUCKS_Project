package com.parkmawani.starbucksproject.vo;

import com.parkmawani.starbucksproject.entity.MenuEntity;

public class MenuInfoVO {
    private String name;
    private Integer price;
    private String explain;
    public MenuInfoVO(MenuEntity menu) {
        this.name = menu.getMbiName();
        this.price = menu.getMbiCost();
        this.explain = menu.getMbiExplain();
    }
}
