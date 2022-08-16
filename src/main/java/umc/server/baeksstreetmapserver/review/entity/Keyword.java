package umc.server.baeksstreetmapserver.review.entity;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;


import javax.persistence.*;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class Keyword {


    @Id
    @Column(nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idx;

    @Column
    private String keyword;

    @Builder
    public Keyword(Long idx, String keyword) {
        this.idx = idx;
        this.keyword = keyword;
    }
}