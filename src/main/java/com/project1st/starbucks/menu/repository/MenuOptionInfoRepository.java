package com.project1st.starbucks.menu.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.project1st.starbucks.menu.entity.MenuBasicInfoEntity;
import com.project1st.starbucks.menu.entity.MenuOptionInfoEntity;



@Repository
public interface MenuOptionInfoRepository extends JpaRepository<MenuOptionInfoEntity, Long> {
    MenuOptionInfoEntity findByMoiSeq(Long moiSeq);
}
