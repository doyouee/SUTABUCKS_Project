package com.project1st.order.menu.vo;

import com.project1st.order.menu.entity.ProductCategoryEntity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductCategoryChildVO {
    private String productCategoryChildName;

    public ProductCategoryChildVO(ProductCategoryEntity productCategoryChild){
        this.productCategoryChildName=productCategoryChild.getPcName();
    }
}
