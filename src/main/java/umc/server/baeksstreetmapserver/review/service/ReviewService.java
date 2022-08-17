package umc.server.baeksstreetmapserver.review.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import umc.server.baeksstreetmapserver.common.Status;
import umc.server.baeksstreetmapserver.review.dto.*;
import umc.server.baeksstreetmapserver.review.entity.Keyword;
import umc.server.baeksstreetmapserver.review.entity.Review;
import umc.server.baeksstreetmapserver.review.entity.ReviewKeyword;
import umc.server.baeksstreetmapserver.review.repository.KeywordRepository;
import umc.server.baeksstreetmapserver.review.repository.ReviewKeywordRepository;
import umc.server.baeksstreetmapserver.review.repository.ReviewRepository;
import umc.server.baeksstreetmapserver.store.entity.Menu;
import umc.server.baeksstreetmapserver.store.entity.Store;
import umc.server.baeksstreetmapserver.store.repository.MenuRepository;
import umc.server.baeksstreetmapserver.store.repository.StoreRepository;

import java.util.List;


@Transactional(readOnly = true)
@RequiredArgsConstructor
@Service
public class ReviewService {

    private final ReviewRepository reviewRepository;
    private final MenuRepository menuRepository;
    private final StoreRepository storeRepository;
    private final KeywordRepository keywordRepository;
    private final ReviewKeywordRepository reviewKeywordRepository;

    @Transactional
    public RegisterReviewResponse registerReview(Long storeIdx, RegisterReviewRequest request) {

        Menu menu = menuRepository.findById(request.getBestMenu()).get();
        Store store = storeRepository.findById(storeIdx).get();

        // dto(데이터-전달-객체)를 entity(db-저장-객체)로 변경
        Review review = request.reviewToEntity(menu, store);

        // 리파지터리에게(db-관리-객체) 전달
        Review saved = reviewRepository.save(review);

        // 키워드
        for(Long keywordIdx : request.getKeyword()) {
            Keyword keyword = keywordRepository.findById(keywordIdx).get();
            // reviewKeyword 엔티티로 변환
            ReviewKeyword reviewKeyword = ReviewKeyword.builder()
                    .review(saved)
                    .keyword(keyword)
                    .build();
            // reviewKeyword 레포에 저장
            reviewKeywordRepository.save(reviewKeyword);
        }
        return new RegisterReviewResponse(review.getIdx());
    }


    @Transactional
    public ModifyReviewResponse modifyReview(Long reviewIdx, ModifyReviewRequest request) {

        Review review = reviewRepository.findById(reviewIdx).get();
        Menu menu = menuRepository.findById(request.getBestMenu()).get();
        // 기존의 keyword 삭제
        List<ReviewKeyword> reviewKeywordList = reviewKeywordRepository.findByReview(review);
        for(ReviewKeyword reviewKeyword : reviewKeywordList) {
            reviewKeywordRepository.delete(reviewKeyword);
        }

        // 요청으로 받은 keyword 등록
        for(Long keywordIdx : request.getKeyword()) {
            Keyword keyword = keywordRepository.findById(keywordIdx).get();
            ReviewKeyword reviewKeyword = ReviewKeyword.builder()
                    .review(review)
                    .keyword(keyword)
                    .build();
            reviewKeywordRepository.save(reviewKeyword);
        }
        review.modify(request.getLike().equals("Y") ? true : false, request.getChange(), request.getReviewText(), menu);
        return new ModifyReviewResponse(reviewIdx, review.getUpdatedAt());
    }


    @Transactional
    public DeleteReviewResponse deleteReview(Long reviewIdx) {
        Review review = reviewRepository.findById(reviewIdx).get();
        List<ReviewKeyword> keywordList = reviewKeywordRepository.findByReview(review);
        for(ReviewKeyword reviewKeyword : keywordList) {
            reviewKeywordRepository.delete(reviewKeyword);
        }
        review.delete();
        return new DeleteReviewResponse(reviewIdx, review.getStatus());
    }


}