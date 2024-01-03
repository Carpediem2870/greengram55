package com.example.greengram33.dm.model;

import lombok.Data;

@Data
public class DmMsgSelVo {
    private int seq; // 글 고유 번호
    private int writerIuser; // 글 쓴사람 번호
    private String writerPic; // 메세지 보낸사람
    private String msg; // 메세지
    private String createdAt; // 각 seq에 대한 메세지 보낸 시간
}