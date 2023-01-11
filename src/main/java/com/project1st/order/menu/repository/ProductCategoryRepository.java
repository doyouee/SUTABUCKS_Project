package com.project1st.order.menu.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.project1st.order.menu.entity.ProductCategoryEntity;

@Repository
public interface ProductCategoryRepository extends JpaRepository<ProductCategoryEntity, Long> {
    // ProductCategoryEntity findByPcParentSeq(ProductCategoryEntity pcEntity);
    
    ProductCategoryEntity findByPcParentSeq(ProductCategoryEntity pcEntity);
}
