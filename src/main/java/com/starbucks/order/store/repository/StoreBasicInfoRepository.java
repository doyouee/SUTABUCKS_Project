package com.starbucks.order.store.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.starbucks.order.store.entity.StoreBasicInfoEntity;

@Repository
public interface StoreBasicInfoRepository extends JpaRepository<StoreBasicInfoEntity, Long> {

}
