package com.project1st.starbucks.admin.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.web.multipart.MultipartFile;

import com.project1st.starbucks.admin.entity.EventEntity;



@Repository
public interface EventRepository extends JpaRepository<EventEntity, Long>{
    // EventEntity findByEventUri(String uri);
    // Integer countBySeq(Long seq);
    public Integer countByEvFile (MultipartFile evFile);
}
