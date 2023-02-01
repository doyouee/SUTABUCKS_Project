package com.project1st.starbucks.rank.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.project1st.starbucks.rank.entity.MemberOrderCountEntity;

@Repository
public interface MemberOrderCountRepository extends JpaRepository<MemberOrderCountEntity, Long> {
    MemberOrderCountEntity findBySbMiSeq(Long miSeq);
}
