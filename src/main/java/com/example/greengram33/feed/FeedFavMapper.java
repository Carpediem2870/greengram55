package com.example.greengram33.feed;

import com.example.greengram33.feed.model.FeedDelDto;
import com.example.greengram33.feed.model.FeedFavDto;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface FeedFavMapper {
    int insFeedFav(FeedFavDto dto);
    List<FeedFavDto> selFeedFavForTest(FeedFavDto dto); // 테스트 용도로 추가함
    int delFeedFav(FeedFavDto dto);
    int FeedDelFav(FeedDelDto dto);
}
