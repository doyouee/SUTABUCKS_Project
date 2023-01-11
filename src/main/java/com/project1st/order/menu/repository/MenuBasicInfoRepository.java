package com.project1st.order.menu.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.project1st.order.menu.entity.MenuBasicInfoEntity;

@Repository
public interface MenuBasicInfoRepository extends JpaRepository<MenuBasicInfoEntity, Long> {
    Page<MenuBasicInfoEntity> findAll(Pageable pageable);
}
