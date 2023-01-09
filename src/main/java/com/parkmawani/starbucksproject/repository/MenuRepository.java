package com.parkmawani.starbucksproject.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.parkmawani.starbucksproject.entity.MenuEntity;

@Repository
public interface MenuRepository extends JpaRepository<MenuEntity, Long>{
    MenuEntity findByMbiSeq(Long mbiSeq);
    List<MenuEntity> listByMbiSeq(Long mbiSeq);
}
