package umc.server.baeksstreetmapserver.review.entity;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import umc.server.baeksstreetmapserver.common.BaseEntity;
import umc.server.baeksstreetmapserver.common.Status;
import umc.server.baeksstreetmapserver.review.entity.Review;

import javax.persistence.*;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class Report extends BaseEntity {


    @Id
    @Column(nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idx;

    @Column(nullable = false)
    private Long reason;

    @Enumerated(EnumType.STRING)
    @Column( nullable = false)
    private Status status;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "review_idx", nullable = false)
    private Review review;

    @Builder
    public Report(Long reason, Status status, Review review) {
        this.reason = reason;
        this.status = status;
        this.review = review;
    }
}