package com.starbucks.final_project01.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.starbucks.final_project01.entity.EventEntity;

@Repository
public interface EventRepository extends JpaRepository<EventEntity, Long>{
    // EventEntity findByEventUri(String uri);
    // Integer countBySeq(Long seq);
}
