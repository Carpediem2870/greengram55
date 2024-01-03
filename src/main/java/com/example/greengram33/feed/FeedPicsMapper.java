package com.example.greengram33.feed;

import com.example.greengram33.feed.model.*;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface FeedPicsMapper {
    int insFeedPics(FeedInsDto dto);
    List<String> selFeedPics(int ifeed);
    int FeedDelPics (int ifeed);
}
