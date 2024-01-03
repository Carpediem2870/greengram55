package com.example.greengram33.user;

import com.example.greengram33.common.Const;
import com.example.greengram33.common.ResVo;
import com.example.greengram33.user.model.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserSerivce {

    private final UserMapper mapper;

    public ResVo signup(UserSignupDto dto) {

        // String salt = BCrypt.gensalt();
        String hashedPw = BCrypt.hashpw(dto.getUpw(),BCrypt.gensalt()); //비밀번호 암호화
        dto.setUpw(hashedPw);

        log.info("befroe - pDto.iuser : {}", dto.getIuser());
        int affected = mapper.userSignup(dto); // 예외처리를 하기위해 사용해야하는 부분
        log.info("after - pDto.iuser : {}", dto.getIuser());
//테스트
        if (affected==0){
            return new ResVo(0);
        }
        return new ResVo(dto.getIuser()); // 회원가입한 iuser pk값이 리턴
    }


    // UserSigninDto는 클라이언트가 입력한 uid, upw 값을 갖고있다
    public UserSigninVo signin(UserSigninDto dto){

        UserSelDto dto1 = new UserSelDto();
        dto1.setUid(dto.getUid());

        UserEntity entity = mapper.selUser(dto1);  // result, iuser, nm, pic담을 vo방생성

        UserSigninVo vo = new UserSigninVo();
        if (entity == null) { // 쿼리문에 uid를 조건으로 받은 select값이 null이면
            vo.setResult(2); // vo에 있는 result에 2를 저장(아이디없음)


            // dto에입력받은비번과 DB에 저장된 pVo에있는 비번이 다를경우
        } else if (!BCrypt.checkpw(dto.getUpw(), entity.getUpw())){ // 순수비번, 암호화된비번
            vo.setResult(3); // vo에 있는 result에 3을 저장(비번틀림)
        } else {
            //나머지 전부 실행
            vo.setResult(1); // vo에 있는 result에 1을 저장
            vo.setFirebaseToken(entity.getFirebaseToken());
            vo.setIuser(entity.getIuser());
            vo.setNm(entity.getNm());
            vo.setPic(entity.getPic());
        }
        return vo;
    }

    public UserInfoVo userInfo(UserInfoSelDto dto){
        return mapper.userInfo(dto);

    }


    public ResVo updUserPic (UserPatchPicDto dto){
        return new ResVo(mapper.updUserPic(dto));
    }

    public ResVo toggleUserFollow(UserFollowDto dto) {
        int affectedFollow = mapper.delUserFollow(dto);

        if (affectedFollow == 1){
            return new ResVo(0);
        }

        mapper.insUserFollow(dto);
        return new ResVo(1);
    }

    public ResVo patchUserFirebaseToken(UserFirebaseTokenPatchDto dto){
        int affectedRows = mapper.updUserFirebaseToken(dto);
        return new ResVo(affectedRows);
    }

}
