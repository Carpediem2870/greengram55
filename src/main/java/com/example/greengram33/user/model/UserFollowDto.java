package com.example.greengram33.user.model;

import lombok.Data;

@Data
public class UserFollowDto {
    private int fromIuser; // 팔로워
    private int toIuser; // 팔로잉
}
