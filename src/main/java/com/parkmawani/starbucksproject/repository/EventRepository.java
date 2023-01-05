package com.parkmawani.starbucksproject.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.parkmawani.starbucksproject.entity.EventEntity;

@Repository
public interface EventRepository extends JpaRepository<EventEntity, Long>{
    // EventEntity findByEventUri(String uri);
    // Integer countBySeq(Long seq);
}
