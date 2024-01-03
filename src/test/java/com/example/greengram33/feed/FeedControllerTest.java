package com.example.greengram33.feed;

import com.example.greengram33.MockMvcConfig;
import com.example.greengram33.common.ResVo;
import com.example.greengram33.feed.model.FeedDelDto;
import com.example.greengram33.feed.model.FeedInsDto;
import com.example.greengram33.feed.model.FeedSelDto;
import com.example.greengram33.feed.model.FeedSelVo;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@MockMvcConfig
@WebMvcTest(FeedController.class)
@Slf4j
class FeedControllerTest {

    @Autowired
    private ObjectMapper mapper;

    @Autowired
    private MockMvc mvc; // post로 보낼지 delete, get, patch로 보낼지 선택해주는기능
    @MockBean
    private FeedService service; // FeedController가 메모리에 올라감(가짜주소값)

    @Test
    void postFeed() throws Exception {

        ResVo result = new ResVo(5); // FeedController에 postFeed메서드의 결과값이 5를 리턴하도록 주입
        //when(service.postFeed(any())).thenReturn(result);
        // service.postFeed가 호출되었을 때 어떤파라미터든 result로 리턴하게 한다.
        given(service.postFeed(any())).willReturn(result); //같은 사용법

        FeedInsDto dto = new FeedInsDto(); // 객체생성
        String json = mapper.writeValueAsString(dto); // json형태의 문자열로 반환
        System.out.println("json: " + json); // json형태의 문자열로 반환
        mvc.perform( // Postman에 있는 Send버튼 구현
                        MockMvcRequestBuilders
                                .post("/api/feed")
                                .contentType(MediaType.APPLICATION_JSON) // 컨텐트타입을 JSON으로 날린다
                                .content(json)
                ) // perform 메소드안에 결과값이
                .andExpect(status().isOk()) //isOk가 200으로 리턴됐는지 확인 // { "result": 5 }
                .andExpect(content().string(mapper.writeValueAsString(result))) //
                .andDo(print()); // 결과를 프린트

        verify(service).postFeed(any());
        // FeedController에 있는 Service에서 postFeed가 호출되었는지 검증

    }


    @SneakyThrows
    @Test
    void selFeedAll() {
        List<FeedSelVo> voList = new ArrayList<>(); // Controller에 있는 getFeedAll메서드의 리턴타입 맞추기위해 객체생성

        FeedSelVo vo = new FeedSelVo(); // FeedSelVo에 있는 Ifeed랑 Contents를 담아서 voList에 넣기위해 객체생성
        vo.setIfeed(7); // ifeed에 7 보냄
        vo.setContents(String.format("index: %d", 7)); // contents에 "7"을 보냄

        FeedSelVo vo2 = new FeedSelVo();
        vo2.setIfeed(9); // ifeed에 7 보냄
        vo2.setContents(String.format("index: %d", 9)); // contents에 "7"을 보냄

        voList.add(vo);
        voList.add(vo2); // voList에 ifeed랑 contents 값넣은거 vo주소값 를 넣어줌

        given(service.FeedSelAll(any())).willReturn(voList); // service.FeedSelAll이 실행되면 voList가 리턴되게함


        //given--  when then
        MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
        // Controller에 있는 selFeedAll에 있는 파라미터 FeedSelDto 접근하기위한 params생성
        params.add("page", "4"); // 입력
        params.add("loginedIuser", "4"); // 입력
        mvc.perform(
                        MockMvcRequestBuilders
                                .get("/api/feed") // 메서드주소
                                .params(params)) // 파라미터값에 MultiValueMap<String, String> params를 보냄(page = 4, loginedIuser = 4)
                .andExpect(status().isOk()) // 상태 200 응답했을 때 = 응답성공 200
                .andExpect(content().string(mapper.writeValueAsString(voList))) // 결과 72번에 있는 VoList와 같아야한다.
                .andDo(print()); // 아래 결과창에 출력문 프린트

        verify(service).FeedSelAll(any()); // Controller에 있는 return service.FeedSelAll이 실행되면 성공.
    }

    @SneakyThrows
    @Test
    void delFeed() {

        ResVo result = new ResVo(5);
        given(service.delFeed(any())).willReturn(result);


        MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
        // Controller에 있는 selFeedAll에 있는 파라미터 FeedSelDto 접근하기위한 params생성
        params.add("ifeed", "1");
        params.add("iuser", "7");// 입력
        mvc.perform(
                MockMvcRequestBuilders
                        .delete("/api/feed")
                        .params(params))
                .andExpect(status().isOk())
                .andExpect(content().string(mapper.writeValueAsString(result)))
                .andDo(print());

        verify(service).delFeed(any());
    }


    @Test
    void toggleFeedFav() {
    }


}