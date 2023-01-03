package com.parkmawani.starbucksproject.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.parkmawani.starbucksproject.entity.EventDetailEntity;

@Repository
public interface EventDetailRepository extends JpaRepository<EventDetailEntity, Long>{
    // EventDetailEntity findByEventDetailUri(String uri);
    
}
