package com.example.greengram33.feed;

import com.example.greengram33.feed.model.FeedDelDto;
import com.example.greengram33.feed.model.FeedFavDto;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface FeedFavMapper {
    int insFeedFav(FeedFavDto dto);
    int delFeedFav(FeedFavDto dto);
    int FeedDelFav(int ifeed);
}
