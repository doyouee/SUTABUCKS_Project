package com.parkmawani.starbucksproject.entity;

import java.time.LocalDate;

import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.DynamicInsert;

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
@Table(name = "event_image")
@DynamicInsert
public class EventEntity {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ev_seq")            private Long evSeq;
    @Column(name = "ev_img_file")       private String evImgFile;
    @Column(name = "ev_uri")            private String evUri;
    @Column(name = "ev_start_date")     private LocalDate evStartDate; 
    @Column(name = "ev_end_date")       private LocalDate evEndDate;
    @Column(name = "ev_content")        private String evContent;
}
