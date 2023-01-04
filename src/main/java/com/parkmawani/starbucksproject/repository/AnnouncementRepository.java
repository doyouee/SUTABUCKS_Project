package com.parkmawani.starbucksproject.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.parkmawani.starbucksproject.entity.AnnouncementEntity;

@Repository
public interface AnnouncementRepository extends JpaRepository<AnnouncementEntity, Long>{
    // AnnouncementEntity findBysaUri(String uri);
    
}
