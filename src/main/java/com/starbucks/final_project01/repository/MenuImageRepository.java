package com.starbucks.final_project01.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.starbucks.final_project01.entity.MenuImageEntity;

@Repository
public interface MenuImageRepository extends JpaRepository<MenuImageEntity, Long>{
    MenuImageEntity findByMiiSeq(Long miiSeq);
}
