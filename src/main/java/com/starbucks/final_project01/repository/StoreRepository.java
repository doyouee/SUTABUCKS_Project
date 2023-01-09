package com.starbucks.final_project01.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.starbucks.final_project01.entity.StoreEntity;

@Repository
public interface StoreRepository extends JpaRepository<StoreEntity, Long>{
    
}
