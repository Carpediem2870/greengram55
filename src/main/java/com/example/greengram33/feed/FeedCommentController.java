package com.example.greengram33.feed;

import com.example.greengram33.common.ResVo;
import com.example.greengram33.feed.model.FeedCommentDelDto;
import com.example.greengram33.feed.model.FeedCommentInsDto;
import com.example.greengram33.feed.model.FeedCommentSelDto;
import com.example.greengram33.feed.model.FeedCommentSelVo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/feed/comment")
public class FeedCommentController {
    private final FeedCommentService service;

    @PostMapping
    public ResVo postFeedComment(@RequestBody FeedCommentInsDto dto){
        log.info("dto: {}", dto);

        return service.postFeedComment(dto); // service에 있는 postFeedComment메서드 리턴
    }

    @GetMapping
    public List<FeedCommentSelVo> getFeedCommentAll(int ifeed){
        log.info("ifeed: {}", ifeed);
        return service.getFeedCommentAll(ifeed);
    }

    @DeleteMapping
    public ResVo delFeedComment(@RequestParam("ifeed_comment")int ifeedComment,
                                @RequestParam("logined_iuser")int loginedIuser){


        FeedCommentDelDto dto = new FeedCommentDelDto();
        dto.setIfeedComment(ifeedComment);
        dto.setLoginedIuser(loginedIuser);

        log.info("dto: {}", dto);
        return service.delFeedComment(dto);
    }
}
