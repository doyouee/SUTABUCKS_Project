package com.project1st.starbucks.member.DTO;

import com.project1st.starbucks.admin.entity.MemberEntity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PostFindIdDTO {
<<<<<<< HEAD
    private String name;
    private String phoneNum;

    public PostFindIdDTO fromEntity(MemberEntity memberInfoEntity) {
        return PostFindIdDTO.builder()
        .name(memberInfoEntity.getMiName())
        .phoneNum(memberInfoEntity.getMiPhoneNum())
=======
    private String miName;
    private String miPhoneNum;

    public PostFindIdDTO fromEntity(MemberEntity memberInfoEntity) {
        return PostFindIdDTO.builder()
        .miName(memberInfoEntity.getMiName())
        .miPhoneNum(memberInfoEntity.getMiPhoneNum())
>>>>>>> main
        .build();
    }
}

