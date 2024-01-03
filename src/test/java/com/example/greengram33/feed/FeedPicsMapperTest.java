package com.example.greengram33.feed;

import com.example.greengram33.feed.model.FeedDelDto;
import com.example.greengram33.feed.model.FeedInsDto;
import com.example.greengram33.feed.model.FeedSelDto;
import com.example.greengram33.feed.model.FeedSelVo;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mybatis.spring.boot.test.autoconfigure.MybatisTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@MybatisTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
// Line17은 H2 경량 데이터베이스를 원래 사용하도록 시스템화 되어있지만
// 기존 mySQL mariaDB 데이터베이스 그대로 사용한다고 선언.
class FeedPicsMapperTest {

    private FeedInsDto dto;

    public FeedPicsMapperTest() {
        dto = new FeedInsDto(); // 객체생성 필요
        List<String> pics = new ArrayList<>();
        pics.add("aaa.jpg");
        pics.add("bbb.jpg");
        dto.setPics(pics);
        dto.setIfeed(5);
    }

    @Autowired
    private FeedPicsMapper picsMapper;

    @BeforeEach
    public void beforeEach() {
        FeedDelDto dto = new FeedDelDto();
        dto.setIfeed(5);
        dto.setIuser(2);
        int affectedRows = picsMapper.FeedDelPics(5); // 피드삭제
        assertEquals(2,affectedRows);
    }

    @Test
    void insFeedPics() {
        List<String> preList = picsMapper.selFeedPics(dto.getIfeed()); // 셀렉트
        assertEquals(0, preList.size());
        int insAffectedRows = picsMapper.insFeedPics(dto);
        assertEquals(dto.getPics().size(), insAffectedRows);

        List<String> afterList = picsMapper.selFeedPics(dto.getIfeed());
        assertEquals(dto.getPics().size(),afterList.size());

        for (int i = 0; i < dto.getPics().size(); i++) {
            assertEquals(dto.getPics().get(i), afterList.get(i));
        }
    }
}