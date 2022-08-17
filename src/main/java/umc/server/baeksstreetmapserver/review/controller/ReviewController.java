package umc.server.baeksstreetmapserver.review.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import umc.server.baeksstreetmapserver.review.dto.*;
import umc.server.baeksstreetmapserver.review.service.ReviewService;

import java.util.List;

@RequiredArgsConstructor
@RequestMapping("/reviews")
@RestController
public class ReviewController {

    private final ReviewService reviewService;

    @PostMapping
    public ResponseEntity<RegisterReviewResponse> registerReview(@RequestParam Long storeIdx, @RequestBody RegisterReviewRequest request) {
        RegisterReviewResponse response = reviewService.registerReview(storeIdx, request);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }


    @PatchMapping("/{reviewIdx}")
    public ResponseEntity<ModifyReviewResponse> modifyReview(@PathVariable Long reviewIdx, @RequestBody ModifyReviewRequest request) {
        ModifyReviewResponse response = reviewService.modifyReview(reviewIdx, request);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @PatchMapping("/{reviewIdx}/status")
    public ResponseEntity<DeleteReviewResponse> deleteReview(@PathVariable Long reviewIdx) {
        DeleteReviewResponse response = reviewService.deleteReview(reviewIdx);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @PostMapping("/{reviewIdx}")
    public ResponseEntity<ReportReviewResponse> reportReview(@PathVariable Long reviewIdx, @RequestBody ReportReviewRequest request) {
        ReportReviewResponse response = reviewService.reportReview(reviewIdx, request);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }



}