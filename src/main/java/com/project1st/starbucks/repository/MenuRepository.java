package com.project1st.starbucks.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.project1st.starbucks.entity.MenuEntity;


@Repository
public interface MenuRepository extends JpaRepository<MenuEntity, Long>{
    MenuEntity findByMbiSeq(Long mbiSeq);
}