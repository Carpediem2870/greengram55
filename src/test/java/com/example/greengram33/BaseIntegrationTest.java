package com.example.greengram33;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
//@Import(CharEncodingConfig.class) // 한글안깨지게함
@MockMvcConfig // UTF-8 ControllerTest때 한글 안깨지게함
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT) // 포트번호는 랜덤이용
@AutoConfigureMockMvc
@Transactional // 트랜젝션 걸려있어서 실제로 DB에 값이 들어가진 않음.
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class BaseIntegrationTest { // Feed 및 User를 통합테스트 할 때 같은내용을 작성하지않도록 상속
    @Autowired protected MockMvc mvc; // Postman
    // MockMvc 타입 으로된 객체화한 주소값 mvc가 생성됨

    @Autowired protected ObjectMapper om; // 통합테스트하는 한테 상속? Controller Service Mapper까지 테스트
    // ObjectMapper 타입으로된 객체화한 주소값 om이 생성됨
}
