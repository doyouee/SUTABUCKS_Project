package com.project1st.order.store.entity;

import com.project1st.order.menu.entity.MenuBasicInfoEntity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "store_menu_connect")
@Builder
public class StoreMenuConnectEntity {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "smc_seq")               private Long smcSeq;
    @Column(name = "smc_menu_stock")        private Integer smcMenuStock;        
    @OneToOne @JoinColumn(name = "smc_sbi_seq") StoreBasicInfoEntity store; // 가게
    // @Column(name = "smc_sbi_seq")           private Integer smcSbiSeq;    // 가게
    @OneToOne @JoinColumn(name = "smc_mbi_seq") MenuBasicInfoEntity menu;   //메뉴
    // @Column(name = "smc_mbi_seq")           private Integer smcMbiSeq;    // 메뉴
}