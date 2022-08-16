package umc.server.baeksstreetmapserver.review.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor

public class RegisterReviewResponse {

    private Long addedReview;

    public RegisterReviewResponse(Long addedReview) {
        this.addedReview = addedReview;
    }

}