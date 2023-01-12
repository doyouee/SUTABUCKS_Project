package com.project1st.starbucks.basket.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.project1st.starbucks.basket.entity.ShoppingBasketOptionEntity;

public interface ShoppingBasketOptionRepository extends JpaRepository<ShoppingBasketOptionEntity, Long>{
    
}
