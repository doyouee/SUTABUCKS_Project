package com.starbucks.final_project01.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.starbucks.final_project01.entity.MenuEntity;

@Repository
public interface MenuRepository extends JpaRepository<MenuEntity, Long>{
    MenuEntity findByMbiSeq(Long mbiSeq);
    List<MenuEntity> listByMbiSeq(Long mbiSeq);
}
