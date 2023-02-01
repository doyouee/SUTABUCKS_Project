package com.project1st.starbucks.rank.entity;

import org.hibernate.annotations.Immutable;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Immutable
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "member_grade_view")
public class MemberOrderCountEntity {
    @Id    
    @Column(name = "sb_mi_seq") private Long sbMiSeq;
    @Column(name = "cnt") private Long cnt;
    @Column(name = "member_grade") private String memberGrade;
}
