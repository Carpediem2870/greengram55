package com.example.greengram33.feed;

import com.example.greengram33.feed.model.FeedDelDto;
import com.example.greengram33.feed.model.FeedInsDto;
import com.example.greengram33.feed.model.FeedSelDto;
import com.example.greengram33.feed.model.FeedSelVo;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface FeedMapper {
    int insFeed(FeedInsDto dto);
    List<FeedSelVo> selFeedAll(FeedSelDto dto);

    int SelFeed(FeedDelDto dto);
    int DelFeedAll(int ifeed);

}
