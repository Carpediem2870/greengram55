package com.example.greengram33.feed;

import com.example.greengram33.feed.model.*;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface FeedCommentMapper {
    int insFeedComment(FeedCommentInsDto dto);
    List<FeedCommentSelVo> selFeedCommentAll(FeedCommentSelDto dto);
    int delFeedComment(FeedCommentDelDto dto);
    int FeedDelComment(int ifeed);
}