package com.project1st.starbucks.basket.entity;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import com.project1st.starbucks.menu.entity.MenuOptionInfoEntity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;


@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Builder
@Table(name = "shopping_basket_option")
// @JsonIdentityInfo(generator = IntSequenceGenerator.class, property = "id")
@JsonIdentityInfo(generator = ObjectIdGenerators.IntSequenceGenerator.class)
public class ShoppingBasketOptionEntity {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "sbo_seq") private Long sboSeq;
    @Column(name = "sbo_number") private Long sboNumber;
    @Column(name = "sbo_moi_seq") private Long sboMoiSeq;
    @Column(name = "sbo_mi_seq") private Long sboMiSeq;
    @ManyToOne
    @JoinColumn(name = "sbo_sb_seq") private ShoppingBasketEntity shoppingBasket;
    // @Column(name = "sbo_sb_seq") private Long sboSbSeq;
}
