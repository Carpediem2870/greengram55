package com.example.greengram33.user;

import com.example.greengram33.user.model.*;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserMapper {

    int userSignup(UserSignupDto dto);
    UserEntity selUser(UserSelDto dto);
    UserInfoVo userInfo(UserInfoSelDto dto);
    int updUserPic(UserPatchPicDto dto);
    int delUserFollow(UserFollowDto dto);
    int insUserFollow(UserFollowDto dto);

    int updUserFirebaseToken(UserFirebaseTokenPatchDto dto);
}
