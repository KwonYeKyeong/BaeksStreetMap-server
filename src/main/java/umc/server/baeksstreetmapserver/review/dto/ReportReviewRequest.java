package umc.server.baeksstreetmapserver.review.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import umc.server.baeksstreetmapserver.common.Status;
import umc.server.baeksstreetmapserver.review.entity.Report;
import umc.server.baeksstreetmapserver.review.entity.Review;
import umc.server.baeksstreetmapserver.store.entity.Menu;
import umc.server.baeksstreetmapserver.store.entity.Store;

@Getter
@NoArgsConstructor
public class ReportReviewRequest {
    private Long reportReason;
}
