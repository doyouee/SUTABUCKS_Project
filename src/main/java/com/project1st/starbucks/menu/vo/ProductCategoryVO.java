package com.project1st.starbucks.menu.vo;

import java.util.List;

import com.project1st.starbucks.menu.entity.ProductCategoryEntity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductCategoryVO {
    public String pcName;
    public List<ProductCategoryChildVO> child;

    public ProductCategoryVO(List<ProductCategoryChildVO> list, ProductCategoryEntity productCategory){
        this.pcName = productCategory.getPcName();
        this.child=list;
    }
}
