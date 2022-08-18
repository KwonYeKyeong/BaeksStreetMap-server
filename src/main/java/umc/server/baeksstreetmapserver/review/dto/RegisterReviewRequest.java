package umc.server.baeksstreetmapserver.review.dto;



import com.fasterxml.jackson.annotation.JsonAutoDetect;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import umc.server.baeksstreetmapserver.common.Status;
import umc.server.baeksstreetmapserver.review.entity.Keyword;
import umc.server.baeksstreetmapserver.review.entity.Review;
import umc.server.baeksstreetmapserver.review.entity.ReviewKeyword;
import umc.server.baeksstreetmapserver.store.entity.Menu;
import umc.server.baeksstreetmapserver.store.entity.Store;
import umc.server.baeksstreetmapserver.user.entity.User;

import java.util.List;

@Getter
@NoArgsConstructor

public class RegisterReviewRequest {

    private List<String> reviewImgUrl;
    private String reviewText;
    private String like;
    private Long change;
    private List<Long> keyword;
    private Long bestMenu;


    @Builder
    public RegisterReviewRequest(List<String> reviewImgUrl, String reviewText, String like, Long change, Long bestMenu, List<Long> keyword) {
        this.reviewImgUrl = reviewImgUrl;
        this.reviewText = reviewText;
        this.like = like;
        this.change = change;
        this.keyword = keyword;
        this.bestMenu = bestMenu;
    }

    //dto -> entity
    public Review reviewToEntity(Menu menu, Store store, User user) {
        return Review.builder()
                .likes(like.equals("Y") ? true : false)
                .changes(change)
                .text(reviewText)
                .menu(menu)
                .status(Status.ACTIVE)
                .store(store)
                .user(user)
                .build();

    }
}