package umc.server.baeksstreetmapserver.review.dto;


import lombok.Getter;
import lombok.NoArgsConstructor;
import umc.server.baeksstreetmapserver.common.Status;

@Getter
@NoArgsConstructor
public class DeleteReviewResponse {
    private Long reviewIdx;
    private Status status;
    public DeleteReviewResponse(Long reviewIdx, Status status) {
        this.reviewIdx = reviewIdx;
        this.status = status;
    }
}
