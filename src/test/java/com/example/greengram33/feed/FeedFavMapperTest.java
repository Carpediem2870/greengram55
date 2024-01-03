package com.example.greengram33.feed;

import com.example.greengram33.feed.model.FeedDelDto;
import com.example.greengram33.feed.model.FeedFavDto;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.mybatis.spring.boot.test.autoconfigure.MybatisTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@Slf4j
@MybatisTest // xml파일(Dao)만 전부다 빈등록 됨.
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
// Line17은 H2 경량 데이터베이스를 원래 사용하도록 시스템화 되어있지만
// 기존 mySQL mariaDB 데이터베이스 그대로 사용한다고 선언.
class FeedFavMapperTest {

    // 스프링에서 객체화해서 빈등록
    @Autowired // final과 @RequiredArgsConstructor을 쓸때 @Autowired 로 주소값을 주입시켜줄 수 있음.
    private FeedFavMapper mapper; // 빈등록되면 (싱글톤)객체주소값 1개 유입됨

    @Test
    public void insFeedFav() {
        FeedFavDto dto = new FeedFavDto();
        dto.setIfeed(27);
        dto.setIuser(2);

        List<FeedFavDto> result3 = mapper.selFeedFavForTest(dto);
        log.info("result3 = {}", result3);
        assertEquals(0,result3.size(),"insert 전 미리 확인");

        int affectedRows1 = mapper.insFeedFav(dto);
        assertEquals(1, affectedRows1);

        List<FeedFavDto> result = mapper.selFeedFavForTest(dto);
        assertEquals(1,result.size(),"첫번째 insert 확인");

        dto.setIfeed(6);
        dto.setIuser(3);

        int affectedRows2 = mapper.insFeedFav(dto);
        assertEquals(1, affectedRows2);

        List<FeedFavDto> result2 = mapper.selFeedFavForTest(dto);
        assertEquals(1,result2.size(),"두번째 insert 확인"); // not null인지 체크
    }

    @Test
    public void delFeedFav() {
        FeedFavDto dto = new FeedFavDto();
        dto.setIfeed(27);
        dto.setIuser(2);

        int affectedRows1 = mapper.delFeedFav(dto);
        assertEquals(0,affectedRows1);

        int affectedRows2 = mapper.delFeedFav(dto);
        assertEquals(0,affectedRows2);

        List<FeedFavDto> result = mapper.selFeedFavForTest(dto);
        assertEquals(1, result.size()); // Null 인지 체크
    }

    @Test
    public void delFeedFavAllTest() {

        FeedFavDto selDto = new FeedFavDto();
        selDto.setIfeed(220);
        List<FeedFavDto> selList = mapper.selFeedFavForTest(selDto);

        FeedDelDto dto = new FeedDelDto();
        dto.setIfeed(220);
        int delAffectedRows = mapper.FeedDelFav(dto);
        assertEquals(selList.size(), delAffectedRows);

        List<FeedFavDto> selList2 = mapper.selFeedFavForTest(selDto);
        assertEquals(0,selList2.size());
    }
}