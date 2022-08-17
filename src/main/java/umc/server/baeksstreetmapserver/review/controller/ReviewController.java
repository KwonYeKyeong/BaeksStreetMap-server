package umc.server.baeksstreetmapserver.review.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import umc.server.baeksstreetmapserver.review.dto.ModifyReviewRequest;
import umc.server.baeksstreetmapserver.review.dto.ModifyReviewResponse;
import umc.server.baeksstreetmapserver.review.dto.RegisterReviewRequest;
import umc.server.baeksstreetmapserver.review.dto.RegisterReviewResponse;
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

}