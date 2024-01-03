package com.example.greengram33.feed;

import com.example.greengram33.common.Const;
import com.example.greengram33.common.ResVo;
import com.example.greengram33.feed.model.*;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/feed")
@Tag(name = "피드 API", description = "피드 관련 처리")

public class FeedController {
    private final FeedService service;


    @Operation(summary = "피드 등록", description = "피드 등록 처리")
    @PostMapping
    public ResVo postFeed(@RequestBody FeedInsDto dto){
        ResVo vo = service.postFeed(dto);
        System.out.println(vo.getResult());
        return vo;
    }

    @GetMapping
    public List<FeedSelVo> selFeedAll(FeedSelDto dto){
        log.info("dto: {}", dto);
        return service.FeedSelAll(dto);
    }

    @GetMapping("/fav")
    public ResVo toggleFeedFav(FeedFavDto dto){
        log.info("dto : {}", dto);
        return service.toggleFeedFav(dto);
    }

    //ifeed iuser
    @DeleteMapping
public ResVo delFeed(FeedDelDto dto){
        log.info("dto: {}", dto);
        return service.delFeed(dto);
    }
}
