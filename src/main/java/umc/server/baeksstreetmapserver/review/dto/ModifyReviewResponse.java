package umc.server.baeksstreetmapserver.review.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
public class ModifyReviewResponse {
    private Long reviewIdx;
    private LocalDateTime updatedAt;

    public ModifyReviewResponse(Long reviewIdx, LocalDateTime updatedAt) {
        this.reviewIdx = reviewIdx;
        this.updatedAt = updatedAt;
    }
}
