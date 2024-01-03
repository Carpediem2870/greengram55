package com.example.greengram33.dm.model;

import lombok.Data;

@Data
public class DmMsgDelDto {
    private int idm;
    private int seq;
    private int iuser; // 해당프로그램은 로그인처리가 안되어있어서받지만 로그인처리를 한다면 받을필요없음

}
