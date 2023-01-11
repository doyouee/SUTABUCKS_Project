package com.project1st.starbucks.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.project1st.starbucks.entity.StoreEntity;


@Repository
public interface StoreRepository extends JpaRepository<StoreEntity, Long>{
    
}
