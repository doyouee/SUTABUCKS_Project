package com.project1st.order.menu.vo;

import com.project1st.order.menu.entity.ProductCategoryEntity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductCategoryChildVO {
    public String pcName;

    public ProductCategoryChildVO(ProductCategoryEntity productCategoryChild){
        this.pcName=productCategoryChild.getPcName();
    }
}
