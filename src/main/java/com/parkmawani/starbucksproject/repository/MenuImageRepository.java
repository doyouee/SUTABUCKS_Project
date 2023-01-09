package com.parkmawani.starbucksproject.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.parkmawani.starbucksproject.entity.MenuImageEntity;

@Repository
public interface MenuImageRepository extends JpaRepository<MenuImageEntity, Long>{
    MenuImageEntity findByMiiSeq(Long miiSeq);
}
