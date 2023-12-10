package com.example.greengram33.feed;

import com.example.greengram33.common.Const;
import com.example.greengram33.common.ResVo;
import com.example.greengram33.feed.model.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class FeedService {
    private final FeedMapper mapper;
    private final FeedPicsMapper picsMapper;
    private final FeedFavMapper favMapper;
    private final FeedCommentMapper commentMapper;

    public ResVo postFeed(FeedInsDto dto) {

        if (dto.getPics().size()==0){
            return new ResVo(2);
        }

        mapper.insFeed(dto);

        // 사진 넣는 insert 쿼리문 실행 후 affectedPic에 저장
        int affectedPic = picsMapper.insFeedPics(dto);
        if (affectedPic != dto.getPics().size()){
            return new ResVo(3); // 입력한 사진갯수와 쿼리에서 실행된 사진갯수가 다름.
        }

        return new ResVo(dto.getIfeed());
    }

    public List<FeedSelVo> FeedSelAll(FeedSelDto dto){ // N + 1
        List<FeedSelVo> list = mapper.selFeedAll(dto); // FeedSelVo 여러개 담을 수 있는 빈방 생성


        // FeedCommentSelDto에 있는 객체를 담을 수 있는 빈방 주소 fcDto 생성
        FeedCommentSelDto fcDto = new FeedCommentSelDto();
        fcDto.setStartIdx(0); // 0번째 0123시작점 // 1이면 1234 // 2면 2345
        fcDto.setRowCount(4); // 노출 개수

        for (FeedSelVo vo: list) { // 반복문을 통해 pics 값 삽입
            List<String> dd = picsMapper.selFeedPics(vo.getIfeed()); // 사진의 주소값을 담을 수 있는 dd 생성
            vo.setPics(dd); // dd의 주소값에 vo에있는 Pics을 담는다.

            fcDto.setIfeed(vo.getIfeed()); // vo에있는 ifeed를 얻어서 fcDto에 vo에있는 ifeed에 저장
            List<FeedCommentSelVo> comments = commentMapper.selFeedCommentAll(fcDto);
            //
            if (comments.size()==4) {
                vo.setIsMoreComment(1); // IsMoreComment는 1은 댓글 더보기 활성화 0은 비활성화
                comments.remove(comments.size()-1); // 배열의 방번호를 지운다.
            }
            vo.setComments(comments);
        }
        return list;
    }

    public ResVo toggleFeedFav(FeedFavDto dto) {

        int affectedRow = favMapper.delFeedFav(dto);

        if (affectedRow == 1){
            return new ResVo(Const.FEED_FAV_DEL);
        }
        favMapper.insFeedFav(dto);
        return new ResVo(Const.FEED_FAV_ADD);
    }

    public ResVo delFeed (FeedDelDto dto) {
        int targetIfeed = mapper.SelFeed(dto);

        if (targetIfeed == 0) {
            return new ResVo(Const.FAIL);
        }
        picsMapper.FeedDelPics(targetIfeed);
        commentMapper.FeedDelComment(targetIfeed);
        favMapper.FeedDelFav(targetIfeed);
        mapper.DelFeedAll(targetIfeed);
        return new ResVo(Const.SUCCESS);
    }
}
