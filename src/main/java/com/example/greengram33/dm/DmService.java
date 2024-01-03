package com.example.greengram33.dm;

import com.example.greengram33.common.ResVo;
import com.example.greengram33.dm.model.*;
import com.example.greengram33.user.UserMapper;
import com.example.greengram33.user.model.UserEntity;
import com.example.greengram33.user.model.UserSelDto;
import com.example.greengram33.user.model.UserSigninVo;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.firebase.messaging.FirebaseMessaging;
import com.google.firebase.messaging.Message;
import com.google.firebase.messaging.Notification;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.catalina.User;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

        @Slf4j
        @Service
        @RequiredArgsConstructor
        public class DmService {
            private final DmMapper mapper;
            private final UserMapper userMapper;
            private final ObjectMapper objMapper;

            public List<DmSelVo> getDmAll(DmSelDto dto) {
                return mapper.selDmAll(dto);
            }


            // 포스트
            public ResVo postDmMsg(DmMsgInsDto dto) {
                int insAffectedRows = mapper.insDmMsg(dto);
                if (insAffectedRows == 1){
                    int updAffectedRows = mapper.updDmLastMsg(dto);
                }

                LocalDateTime now = LocalDateTime.now(); // 현재 날짜 구하기
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"); // 포맷 정의
                String createdAt = now.format(formatter); // 포맷 적용

                //상대방의 firebaseToken값 필요. 나의 pic, iuser값 필요.
                UserEntity otherPerson = mapper.selOtherPersonByLoginUser(dto);

                try {

                    if (otherPerson.getFirebaseToken() != null) {
                        DmMsgPushVo pushVo = new DmMsgPushVo();
                        pushVo.setIdm(dto.getIdm());
                        pushVo.setSeq(dto.getSeq());
                        pushVo.setWriterIuser(dto.getLoginedIuser());
                        pushVo.setWriterPic(dto.getLoginedPic());
                        pushVo.setMsg(dto.getMsg());
                        pushVo.setCreatedAt(createdAt);

                        //object를 json으로 변경
                        String body = objMapper.writeValueAsString(pushVo);
                        log.info("body: {}", body);

                        Notification noti = Notification.builder()
                                .setTitle("dm") // 제목작성, 프론트에서 쓰는 분기용
                                .setBody(body) // 내용
                                .build();

                        Message message = Message.builder() // 노티피케이션을 담고
                                .setToken(otherPerson.getFirebaseToken()) // 어디로보낼지

                                .setNotification(noti) // 노티피케이션에 담고 message에 객체화
                                .build();

                        FirebaseMessaging.getInstance().sendAsync(message);

                        // Async 비동기 - 내가 움직이는것과 상관없이 상대방도 움직일수 있음
                        // 동기 - 턴게임(장기, 체스) 내가 움직이면 상대방이 못움직임
                        // 스레드 - 동작단위 (게임예 : 총게임에 캐릭터 한명한명)
                        // 메인스레드는 통신 하지말것


                        /*FirebaseMessaging fm = FirebaseMessaging.getInstance(); // 싱글톤
                        fm.sendAsync(massage);*/
                    }
                } catch (Exception e) {
                e.printStackTrace();
            }
        return new ResVo(dto.getSeq());
        }


            public List<DmMsgSelVo> getMsgAll(DmMsgSelDto dto) {
                return mapper.selDmMsgAll(dto);
            }


            public ResVo dmDelMsg(DmMsgDelDto dto){

                int delAffectedRows = mapper.dmDelMsg(dto);
                if (delAffectedRows==1){
                    int updAffectedRows = mapper.updDmLastMsgAfterDelByLastMsg(dto);
                }
                return new ResVo(delAffectedRows);
            }


            public DmSelVo postDm(DmInsDto dto){
                Integer isExistDm = mapper.selDmUserCheck(dto);
                log.info("dto: {}", dto);
                if (isExistDm != null) {
                    return null;
                }

                mapper.insDm(dto); // 빈 방 만들기 idm 값 생성
                mapper.insDmUser(DmUserInsDto.builder() // 로그인한 유저 해당 방에 참여 시키는 과정
                                .idm(dto.getIdm()) // 빈 방 생성시 만들어진 idm값 가져옴
                                .iuser(dto.getLoginedIuser())
                        .build());

                mapper.insDmUser(DmUserInsDto.builder() // 다른 유저 해당 방에 참여
                                .idm(dto.getIdm()) // 빈 방 생성시 만들어진 idm값 가져옴
                                .iuser(dto.getOtherPersonIuser()) //postman에서 입력받은 상대 iuser값 가져옴
                        .build());
                // 여기까지 새로 생성된 채팅방에 유저 집어넣기완료

                UserSelDto usDto = new UserSelDto(); // UserMapper.xml에 있는 selUser를 실행하기 위해 객체생성
                usDto.setIuser(dto.getOtherPersonIuser()); // 새로운객체 usDto에 dto에서 입력받은 상대 iuser값 가져옴

                UserEntity entity = userMapper.selUser(usDto); // 다른 유저의 정보 얻어오기
                return DmSelVo.builder() // 빌더로 값을 불러온다.
                        .idm(dto.getIdm()) // 위에서 생성된 방번호 가져오기
                        .otherPersonIuser(entity.getIuser()) // 다른 유저의 iuser 가져오기
                        .otherPersonNm(entity.getNm()) // 다른 유저의 nm 가져오기
                        .otherPersonPic(entity.getPic()) // 다른 유저의 사진 가져오기
                        .build();
            }


}
