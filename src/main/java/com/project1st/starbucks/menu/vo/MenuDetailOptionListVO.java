package com.project1st.starbucks.menu.vo;

import java.util.List;


import lombok.Data;

@Data
public class MenuDetailOptionListVO {
    private Long storeMenuNo;
    private MenuDetailVO detail;
    private List<MenuOptionListVO> options;
}
