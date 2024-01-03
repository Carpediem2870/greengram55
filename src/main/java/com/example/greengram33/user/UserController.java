package com.example.greengram33.user;

import com.example.greengram33.common.ResVo;
import com.example.greengram33.user.model.*;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/user")
public class UserController {
    private final UserSerivce service;

    @Operation(summary = "회원가입", description = "회원가입 처리")
    @PostMapping("/signup")
    public ResVo postSignup(@RequestBody UserSignupDto dto){
        log.info("dto: {}", dto);
        return service.signup(dto);
    }

    //pk, 이름, 프로필사진
    @PostMapping("/signin")
    public UserSigninVo postSignin(@RequestBody UserSigninDto dto){
        log.info("dto: {}", dto);
        return service.signin(dto); // result - 1: 성공, 2: 아이디없음, 3: 비밀번호 틀림
    }

    @GetMapping
    public UserInfoVo userInfoVo(UserInfoSelDto dto){
        return service.userInfo(dto);
    }

    @PatchMapping("/pic")
    public ResVo updUser(@RequestBody UserPatchPicDto dto) {

        return service.updUserPic(dto);
    }

    // ------------ follow
    // ResVo - result: 1- Following, 0- 취소
    @PostMapping("/follow")
    public ResVo toggleFollow(@RequestBody UserFollowDto dto) {
        return service.toggleUserFollow(dto);
    }

    @PatchMapping("/firebase-token")
    public ResVo patchUserFirebaseToken(@RequestBody UserFirebaseTokenPatchDto dto){
        return service.patchUserFirebaseToken(dto);
    }

}
