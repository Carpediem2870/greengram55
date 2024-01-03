package com.example.greengram33.dm.model;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class DmUserInsDto {
    private int idm; // 방 번호
    private int iuser; // 유저 번호

}
