package com.example.greengram33.feed;

import com.example.greengram33.BaseIntegrationTest;
import com.example.greengram33.common.ResVo;
import com.example.greengram33.feed.model.FeedDelDto;
import com.example.greengram33.feed.model.FeedInsDto;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

//Controller, Service, Mapper이 진짜로 값이 들어감
public class FeedIntegrationTest extends BaseIntegrationTest {

    @Test
    @Rollback(false)
    public void postFeed() throws Exception {
        FeedInsDto dto = new FeedInsDto(); // 객체생성
        dto.setIuser(3);
        dto.setContents("통합 테스트 작업 3");
        dto.setLocation("그린컴퓨터학원3");

        List<String> pics = new ArrayList<>();
        pics.add("https://search.pstatic.net/common/?src=https%3A%2F%2Fshopping-phinf.pstatic.net%2Fmain_8007831%2F80078318393.jpg&type=f372_372");
        pics.add("https://search.pstatic.net/common/?src=http%3A%2F%2Fblogfiles.naver.net%2FMjAyMzEyMDZfMjI4%2FMDAxNzAxODM5OTgyNzU4.90Gp23wsdm1QCdRixaKG1m8OdA6EhSsS3YTTK5cM3eMg.4Z4zOSmuaryGBryLAWWBd6jca-CyP64ZlnSPiIJZVDMg.JPEG.kwonparis%2F%25C4%25BF%25C7%25C3_%25C2%25AF%25B1%25B8.jpg&type=ofullfill340_600_png");
        dto.setPics(pics);

        String json = om.writeValueAsString(dto); // json형태의 문자열로 변환
        System.out.println("json: " + json); // json형태의 문자열로 반환

        MvcResult mr = mvc.perform( // Postman에 있는 Send버튼 구현
                        MockMvcRequestBuilders
                                .post("/api/feed") // 포스트방식으로 주소값을 담아 post방식으로 날린다.
                                .contentType(MediaType.APPLICATION_JSON) // 컨텐트타입을 JSON으로 날린다.
                                .content(json) // json 내용을 BODY에 담아서 보냄
                ) // perform 메소드안에 결과값이
                .andExpect(status().isOk()) //isOk가 200으로 리턴됐는지 확인 // { "result": 1 or 0 }
                .andDo(print()) // 결과를 프린트
                .andReturn(); // 결과가 mr에 담김

        String content = mr.getResponse().getContentAsString();
        ResVo vo = om.readValue(content, ResVo.class); // 객체로 바꾸기
        assertEquals(true, vo.getResult()>0); // ifeed값이 넘어옴
    }

    @Test
    @Rollback(false)
    public void delFeed() throws Exception {

        MultiValueMap<String, String> qurry = new LinkedMultiValueMap<>();
        qurry.add("iuser", "3");
        qurry.add("ifeed", "28");

        MvcResult mr = mvc.perform(
                MockMvcRequestBuilders
                        //.delete("/api/feed?ifeed={ifeed}&iuser={iuser}", "221", "3")
                        .delete("/api/feed")
                        .params(qurry)
        )
                .andExpect(status().isOk())
                .andDo(print())
                .andReturn();

        String content = mr.getResponse().getContentAsString();
        ResVo vo = om.readValue(content, ResVo.class); // 객체로 바꾸기
        assertEquals(1, vo.getResult()); // ifeed값이 넘어옴
    }

}
