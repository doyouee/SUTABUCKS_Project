package com.parkmawani.starbucksproject.entity;

import java.time.LocalDate;

import org.hibernate.annotations.ColumnDefault;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "member_info")
public class MemberEntity {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "mi_seq")        private Long miSeq;
    @Column(name = "mi_id") private String miId;
    @Column(name = "mi_pwd") private String miPwd;
    @Column(name = "mi_name") private String miName;
    @Column(name = "mi_nickname") private String miNickname;
    @Column(name = "mi_birth") private LocalDate miBirth;
    @Column(name = "mi_gen") @ColumnDefault("0")
    private Integer miGen;
    @Column(name = "mi_group") private Integer miGroup;
    @Column(name = "mi_business_num") private String miBusinessNum;
    @Column(name = "mi_detail_address") private String miDetailAddress;
    @Column(name = "mi_last_login") private LocalDate miLastLogin;
    @Column(name = "mi_status") @ColumnDefault("0")
    private Integer miStatus;
    @Column(name = "mi_phonenum") private String miPhonenum;

}
