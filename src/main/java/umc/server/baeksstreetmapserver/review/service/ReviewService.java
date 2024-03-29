package umc.server.baeksstreetmapserver.review.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import umc.server.baeksstreetmapserver.common.Status;
import umc.server.baeksstreetmapserver.common.error.exception.UserAccessDeniedException;
import umc.server.baeksstreetmapserver.review.dto.*;
import umc.server.baeksstreetmapserver.review.entity.Keyword;
import umc.server.baeksstreetmapserver.review.entity.Report;
import umc.server.baeksstreetmapserver.review.entity.Review;
import umc.server.baeksstreetmapserver.review.entity.ReviewKeyword;
import umc.server.baeksstreetmapserver.review.repository.KeywordRepository;
import umc.server.baeksstreetmapserver.review.repository.ReportRepository;
import umc.server.baeksstreetmapserver.review.repository.ReviewKeywordRepository;
import umc.server.baeksstreetmapserver.review.repository.ReviewRepository;
import umc.server.baeksstreetmapserver.store.entity.Menu;
import umc.server.baeksstreetmapserver.store.entity.Store;
import umc.server.baeksstreetmapserver.store.repository.MenuRepository;
import umc.server.baeksstreetmapserver.store.repository.StoreRepository;
import umc.server.baeksstreetmapserver.user.entity.User;
import umc.server.baeksstreetmapserver.user.repository.UserRepository;
import umc.server.baeksstreetmapserver.utils.JwtService;


import java.text.MessageFormat;
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
    private final ReportRepository reportRepository;
    private final UserRepository userRepository;
    private final JwtService jwtService;

    @Transactional

    public RegisterReviewResponse registerReview(Long storeIdx, RegisterReviewRequest request) throws Exception {

        Menu menu = menuRepository.findById(request.getBestMenu()).get();
        Store store = storeRepository.findById(storeIdx).get();

        // jwt로 유저객체 가져오기
        Long userIdxByJwt = jwtService.getUserIdx();
        User user = userRepository.findById(userIdxByJwt).get();

        // dto(데이터-전달-객체)를 entity(db-저장-객체)로 변경
        Review review = request.reviewToEntity(menu, store, user);

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
    public ModifyReviewResponse modifyReview(Long reviewIdx, ModifyReviewRequest request) throws Exception {

        Review review = reviewRepository.findById(reviewIdx).get();
        Menu menu = menuRepository.findById(request.getBestMenu()).get();
        Long userIdxByJwt = jwtService.getUserIdx();

        // 자신이 작성한 리뷰만 수정 가능
        if (!review.isWrittenBy(userIdxByJwt)) {
            throw new UserAccessDeniedException(MessageFormat.format("리뷰 작성자가 아닙니다. userId = {0}", userIdxByJwt));
        }

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
    public DeleteReviewResponse deleteReview(Long reviewIdx) throws Exception {
        Review review = reviewRepository.findById(reviewIdx).get();
        List<ReviewKeyword> keywordList = reviewKeywordRepository.findByReview(review);
        Long userIdxByJwt = jwtService.getUserIdx();

        // 자신이 작성한 리뷰만 삭제 가능
        if (!review.isWrittenBy(userIdxByJwt)) {
            throw new UserAccessDeniedException(MessageFormat.format("리뷰 작성자가 아닙니다. userId = {0}", userIdxByJwt));
        }

        for(ReviewKeyword reviewKeyword : keywordList) {
            reviewKeywordRepository.delete(reviewKeyword);
        }
        review.delete();
        return new DeleteReviewResponse(reviewIdx, review.getStatus());
    }

    @Transactional
    public ReportReviewResponse reportReview(Long reviewIdx, ReportReviewRequest request) {
        Review review = reviewRepository.findById(reviewIdx).get();
        Report report = Report.builder()
                .reason(request.getReportReason())
                .status(Status.ACTIVE)
                .review(review)
                .build();
        reportRepository.save(report);

        // 3번 신고되면 비활성화
        List<Report> reportList = reportRepository.findByReview(review);
        if(reportList.size() >= 3) {
            review.delete();
        }
        return new ReportReviewResponse(review.getIdx());

    }

}