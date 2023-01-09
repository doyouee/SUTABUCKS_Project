package com.starbucks.final_project01.store.vo;

import com.starbucks.final_project01.api.menu.entity.MenuBasicInfoEntity;

import lombok.Data;

@Data
public class MenuInfoVO {
    private Long menuNo;
    private String name;
    private Integer cost;
    private Integer status;
    private String explain;
    private Integer categoryNo;

    public MenuInfoVO(MenuBasicInfoEntity entity) {
        this.menuNo = entity.getMbiSeq();
        this.name = entity.getMbiName();
        this.cost = entity.getMbiCost();
        this.status = entity.getMbiStatus();
        this.explain = entity.getMbiExplain();
        this.categoryNo = entity.getMbiPcSeq();
    }
}
