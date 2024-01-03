package com.example.greengram33.dm.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

@Data
public class DmInsDto {

    @JsonIgnore
    private int idm; // 채팅방 고유번호 // Auto Increment값을 가져오기 위해 만듦 // 방만들고 프론트로부터 받는자료


    private int loginedIuser;
    private int otherPersonIuser;
}
