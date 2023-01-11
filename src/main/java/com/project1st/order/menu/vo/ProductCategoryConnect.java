package com.project1st.order.menu.vo;

import lombok.Data;

@Data
public class ProductCategoryConnect {
    private ProductCategoryVO parentCategory;
    private ProductCategoryChildVO childCategory;
}
