package com.example.greengram33.dm;

import com.example.greengram33.common.ResVo;
import com.example.greengram33.dm.model.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

        @Slf4j
        @Service
        @RequiredArgsConstructor
        public class DmService {
            private final DmMapper mapper;

            public List<DmSelVo> getDmAll(DmSelDto dto) {
                return mapper.selDmAll(dto);
            }


            public ResVo postDmMsg(DmMsgInsDto dto) {
                int affectedRows = mapper.insDmMsg(dto);
                return new ResVo(dto.getSeq());
            }
            public List<DmMsgSelVo> getMsgAll(DmMsgSelDto dto) {
                return mapper.selDmMsgAll(dto);
            }
}
