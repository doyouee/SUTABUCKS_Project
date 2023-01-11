package com.project1st.starbucks.DTO;

import com.project1st.starbucks.entity.MemberInfoEntity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PostLoginDTO {
    // private Long seq;
    private String id;
    private String pwd;

    public static PostLoginDTO fromEntity(MemberInfoEntity memberInfoEntity){
        return PostLoginDTO.builder()
        // .seq(memberInfoEntity.getMiSeq())
        .id(memberInfoEntity.getMiId())
        .pwd(memberInfoEntity.getMiPwd())
        .build();

    }
        
    }

