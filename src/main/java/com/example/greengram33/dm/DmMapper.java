package com.example.greengram33.dm;

import com.example.greengram33.dm.model.*;
import com.example.greengram33.user.model.UserEntity;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface DmMapper {
    //----------------------- t_dm
    int insDm(DmInsDto dto);
    List<DmSelVo> selDmAll(DmSelDto dto);

    //----------------------- t_dm_user
    int insDmUser(DmUserInsDto dto);
    Integer selDmUserCheck(DmInsDto dto);

    //----------------------- t_dm_msg
    int insDmMsg(DmMsgInsDto dto);
    List<DmMsgSelVo> selDmMsgAll(DmMsgSelDto dto);
    int updDmLastMsgAfterDelByLastMsg(DmMsgDelDto dto);

    int updDmLastMsg(DmMsgInsDto dto);
    int dmDelMsg(DmMsgDelDto dto);

    UserEntity selOtherPersonByLoginUser(DmMsgInsDto dto);
}
