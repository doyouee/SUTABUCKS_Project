package com.project1st.starbucks.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.project1st.starbucks.entity.AnnouncementEntity;


@Repository
public interface AnnouncementRepository extends JpaRepository<AnnouncementEntity, Long>{
    // AnnouncementEntity findBysaUri(String uri);
    
}
