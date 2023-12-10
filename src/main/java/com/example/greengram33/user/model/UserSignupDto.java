package com.example.greengram33.user.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
@Schema(title = "회원가입 데이터")
public class UserSignupDto {

    @JsonIgnore
    private int iuser;

    @Schema(title = "유저 id")
    private String uid;

    @Schema(title = "유저 pw")
    private String upw;

    @Schema(title = "유저 nm")
    private String nm;

    @Schema(title = "유저 pic")
    private String pic;
}
