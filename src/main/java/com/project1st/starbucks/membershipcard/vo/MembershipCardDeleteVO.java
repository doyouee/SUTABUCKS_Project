package com.project1st.starbucks.membershipcard.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MembershipCardDeleteVO {
    private String cardNo;
    private String cardName;
    private String cardMoney;
    private String cardUserNo;

    public void copyData(MembershipCardDeleteVO data) {
        if(data.cardNo != null)   this.cardNo = data.getCardNo();
        if(data.cardName != null)   this.cardName = data.getCardName();
        if(data.cardMoney != null)   this.cardMoney = data.getCardMoney();
        if(data.cardUserNo != null)   this.cardUserNo = data.getCardUserNo();
    }




}
