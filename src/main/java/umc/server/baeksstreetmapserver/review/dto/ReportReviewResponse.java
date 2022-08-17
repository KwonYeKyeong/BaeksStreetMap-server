package umc.server.baeksstreetmapserver.review.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class ReportReviewResponse {
    private Long reportedReview;

    public ReportReviewResponse(Long reportedReview) {
        this.reportedReview = reportedReview;
    }
}
