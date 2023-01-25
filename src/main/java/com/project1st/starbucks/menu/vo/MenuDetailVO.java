package com.project1st.starbucks.menu.vo;

import com.project1st.starbucks.admin.entity.MenuImageEntity;
import com.project1st.starbucks.menu.entity.MenuBasicInfoEntity;
import com.project1st.starbucks.menu.entity.MenuQrEntity;

import lombok.Data;

@Data
public class MenuDetailVO {
    private Long menuSeq;
    private String menuName;
    private Integer menuCost;
    private Integer menuStatus;
    private String menuExplain;
    private Integer menuCategorySeq;
    private String menuFile;
    private String menuUri;
    private String menuQrFile;
    private String menuQrUri;

    public MenuDetailVO(MenuBasicInfoEntity basic, MenuImageEntity image, MenuQrEntity qr) {
        this.menuSeq = basic.getMbiSeq();
        this.menuName = basic.getMbiName();
        this.menuCost = basic.getMbiCost();
        this.menuStatus = basic.getMbiStatus();
        this.menuExplain = basic.getMbiExplain();
        this.menuCategorySeq = basic.getMbiPcSeq();
        this.menuFile = image.getMiiImgFile();
        this.menuUri = image.getMiiUri();
        this.menuQrFile = qr.getMqiImageFile();
        this.menuQrUri = qr.getMqiUri();
    }
}
