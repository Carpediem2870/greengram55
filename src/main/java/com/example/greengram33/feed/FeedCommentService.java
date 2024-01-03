package com.example.greengram33.feed;

import com.example.greengram33.common.ResVo;
import com.example.greengram33.feed.FeedCommentMapper;
import com.example.greengram33.feed.model.FeedCommentDelDto;
import com.example.greengram33.feed.model.FeedCommentInsDto;
import com.example.greengram33.feed.model.FeedCommentSelDto;
import com.example.greengram33.feed.model.FeedCommentSelVo;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor // 생성자 주입
public class FeedCommentService {
    private final FeedCommentMapper mapper;

    // 댓글 작성
    public ResVo postFeedComment(FeedCommentInsDto dto){
        int affectedRows = mapper.insFeedComment(dto); // 커리문 실행

        return new ResVo(dto.getIfeedComment()); // 실행된 ifeedComment 값 반환
    }


    public List<FeedCommentSelVo> getFeedCommentAll(int ifeed){

        FeedCommentSelDto selDto = new FeedCommentSelDto();
        selDto.setIfeed(ifeed);
        selDto.setStartIdx(3);
        selDto.setRowCount(999);
        return mapper.selFeedCommentAll(selDto);
    }

    public ResVo delFeedComment(FeedCommentDelDto dto){
        return new ResVo(mapper.delFeedComment(dto));
    }
}
