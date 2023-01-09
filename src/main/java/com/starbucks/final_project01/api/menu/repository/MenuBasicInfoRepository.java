package com.starbucks.final_project01.api.menu.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.starbucks.final_project01.api.menu.entity.MenuBasicInfoEntity;

@Repository
public interface MenuBasicInfoRepository extends JpaRepository<MenuBasicInfoEntity, Long> {
    public MenuBasicInfoEntity findByMbiSeq(Integer mbiSeq);
}
