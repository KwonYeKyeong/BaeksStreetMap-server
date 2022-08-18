package umc.server.baeksstreetmapserver.review.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@NoArgsConstructor

public class ModifyReviewRequest {
    private String reviewText;
    private String like;
    private Long change;
    private List<Long> keyword;
    private Long bestMenu;
}
