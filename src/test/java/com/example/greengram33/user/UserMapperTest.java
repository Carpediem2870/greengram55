package com.example.greengram33.user;

import com.example.greengram33.common.ResVo;
import com.example.greengram33.user.model.UserEntity;
import com.example.greengram33.user.model.UserSignupDto;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.mybatis.spring.boot.test.autoconfigure.MybatisTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;

import static org.junit.jupiter.api.Assertions.assertEquals;

@Slf4j
@MybatisTest // xml파일(Dao)만 전부다 빈등록 됨.
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class UserMapperTest {

    @Autowired
    private UserMapper userMapper;


    @Test
    public void userSignup() {
        UserSignupDto dto = new UserSignupDto();
        dto.setUid("mic55555");
        dto.setUpw("1212");
        dto.setNm("테스트이름");
        dto.setPic("test.jpg");

        int result = userMapper.userSignup(dto);

        log.info("result = {}", result);
        assertEquals(1,result);



        assertEquals(true, dto.getIuser()>0);

        UserEntity user = new UserEntity();
        user.setUid(dto.getUid());
        user.setUpw(dto.getUpw());
        user.setNm(dto.getNm());
        user.setPic(dto.getPic());
    }
}
