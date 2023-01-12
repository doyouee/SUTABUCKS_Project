package com.project1st.starbucks.basket.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.project1st.starbucks.basket.entity.ShoppingBasketEntity;


@Repository
public interface ShoppingBasketRepository extends JpaRepository<ShoppingBasketEntity, Long> {
    public List<ShoppingBasketEntity> findBySbMiSeqAndSbStatus(Long sbMiSeq, Long sbStatus);    
}
