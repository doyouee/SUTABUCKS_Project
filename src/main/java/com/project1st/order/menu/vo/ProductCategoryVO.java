package com.project1st.order.menu.vo;

import java.util.List;

import com.project1st.order.menu.entity.ProductCategoryEntity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductCategoryVO {
    private String pcName;
    private List<ProductCategoryChildVO> productCategoryChild;

    public ProductCategoryVO(List<ProductCategoryChildVO> list, ProductCategoryEntity productCategory){
        this.pcName = productCategory.getPcName();
        this.productCategoryChild=list;
    }

    

}
