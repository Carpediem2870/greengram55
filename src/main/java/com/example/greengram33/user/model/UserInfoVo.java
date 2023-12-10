package com.example.greengram33.user.model;

import lombok.Data;

@Data // logined iuser 값도 받아야함 userinfoseldto에서
public class UserInfoVo {
    private String nm;
    private String pic;
    private String createdAt;
    private int feedCnt;
    private int favCnt;
    private int follower; // 팔로워 수(targetIuser를 팔로우 하는 사람)
    private int following; // 팔로잉 수(targetIuser가 팔로우 하는 사람)
    private int followState;

/*  0 - 둘다 안함
    1 - loginedIuser가 targetIuser를 팔로우
    2 - targetIuser가 loginedIuser를 팔로우
    3 - 둘다 팔로우  */

}
