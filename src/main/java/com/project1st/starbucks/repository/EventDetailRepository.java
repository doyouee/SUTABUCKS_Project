package com.project1st.starbucks.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.project1st.starbucks.entity.EventDetailEntity;


@Repository
public interface EventDetailRepository extends JpaRepository<EventDetailEntity, Long>{
    // EventDetailEntity findByEventDetailUri(String uri);
    //  EventDetailEntity findByEventDetailUri(String uri);
}