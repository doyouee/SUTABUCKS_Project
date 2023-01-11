package com.project1st.starbucks.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.project1st.starbucks.entity.EventEntity;



@Repository
public interface EventRepository extends JpaRepository<EventEntity, Long>{
    // EventEntity findByEventUri(String uri);
    // Integer countBySeq(Long seq);
}
