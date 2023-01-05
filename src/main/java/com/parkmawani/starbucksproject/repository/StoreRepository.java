package com.parkmawani.starbucksproject.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.parkmawani.starbucksproject.entity.StoreEntity;

@Repository
public interface StoreRepository extends JpaRepository<StoreEntity, Long>{
    
}
