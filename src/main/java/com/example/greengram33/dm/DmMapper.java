package com.example.greengram33.dm;

import com.example.greengram33.dm.model.*;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface DmMapper {
    //----------------------- t_dm
    int insDm(DmInsDto dto);
    List<DmSelVo> selDmAll(DmSelDto dto);

    //----------------------- t_dm_user
    int insDmuser(DmUserInsDto dto);

    //----------------------- t_dm_msg
    int insDmMsg(DmMsgInsDto dto);
    List<DmMsgSelVo> selDmMsgAll(DmMsgSelDto dto);
}
