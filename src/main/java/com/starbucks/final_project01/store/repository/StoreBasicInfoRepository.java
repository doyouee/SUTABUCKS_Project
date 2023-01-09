package com.starbucks.final_project01.store.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.starbucks.final_project01.store.entity.StoreBasicInfoEntity;

@Repository
public interface StoreBasicInfoRepository extends JpaRepository<StoreBasicInfoEntity, Long> {

}
