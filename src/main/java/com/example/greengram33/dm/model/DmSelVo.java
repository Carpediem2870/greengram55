package com.example.greengram33.dm.model;

import lombok.Builder;
import lombok.Data;
import lombok.Getter;

@Data
@Builder
public class DmSelVo { // 채팅 리스트 띄워주는 모델 // 방 만들고 응답해주는 자료
    private int idm; // 채팅 방 번호
    private String lastMsg; // 마지막 메세지 내용
    private String lastMsgAt; // 마지막 메세지 보낸 시간
    private int otherPersonIuser;
    private String otherPersonNm; // 상대방 이름 // 가져올 때 유저매퍼 사용
    private String otherPersonPic; // 상대방 프로필 사진 // 가져올 때 유저매퍼 사용

}
