package com.parkmawani.starbucksproject.entity;

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
@Table(name = "event_detail_image")
public class EventDetailEntity {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "edi_seq")    private Long ediSeq;
    @Column(name = "edi_img_file")    private Long ediImgFile;
    @Column(name = "edi_start_date")    private Long ediStartDate;
    @Column(name = "edi_end_date")    private Long ediEndDate;
    @Column(name = "edi_contents")    private Long ediContents;
    @Column(name = "edi_uri")    private Long ediUri;

}
