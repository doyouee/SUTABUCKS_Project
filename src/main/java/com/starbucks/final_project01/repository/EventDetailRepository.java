package com.starbucks.final_project01.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.starbucks.final_project01.entity.EventDetailEntity;

@Repository
public interface EventDetailRepository extends JpaRepository<EventDetailEntity, Long>{
    // EventDetailEntity findByEventDetailUri(String uri);
    //  EventDetailEntity findByEventDetailUri(String uri);
}
