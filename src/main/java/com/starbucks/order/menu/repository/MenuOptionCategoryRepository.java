package com.starbucks.order.menu.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.starbucks.order.menu.entity.MenuOptionCategoryEntity;

@Repository
public interface MenuOptionCategoryRepository extends JpaRepository<MenuOptionCategoryEntity, Long> {
    
}
