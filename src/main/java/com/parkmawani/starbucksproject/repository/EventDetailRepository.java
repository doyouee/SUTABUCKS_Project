package com.parkmawani.starbucksproject.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EventDetailRepository extends JpaRepository<EventDetailRepository, Long>{
    EventDetailRepository findByEventDetailUri(String uri);
    
}
