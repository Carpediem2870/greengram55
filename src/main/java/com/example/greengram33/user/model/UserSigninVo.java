package com.example.greengram33.user.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
public class UserSigninVo {

    @JsonIgnore
    private String upw;

    private int result; // 실행관련 정보 출력을 위해 생성
    private int iuser;
    private String nm;
    private String pic;
    private String firebaseToken;
}
