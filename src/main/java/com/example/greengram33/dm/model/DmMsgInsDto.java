package com.example.greengram33.dm.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

@Data
public class DmMsgInsDto {
    private int idm; // 채팅방 고유번호

    @JsonIgnore
    private int seq; // 각 채팅방의 메세지 번호
    private int loginedIuser; // 로그인 유저 PK
    private String loginedPic; // 상대방에 보여줄 나의 프로필 사진
    private String msg; // 메세지 내용
}
