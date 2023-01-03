package com.parkmawani.starbucksproject.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.parkmawani.starbucksproject.entity.EventEntity;

public interface EventRepository extends JpaRepository<EventEntity, Long>{
    EventEntity findByEventUri(String uri);
    
}
