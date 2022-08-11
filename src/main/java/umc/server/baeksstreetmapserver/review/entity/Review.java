package umc.server.baeksstreetmapserver.review.entity;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import umc.server.baeksstreetmapserver.common.BaseEntity;
import umc.server.baeksstreetmapserver.common.Status;
import umc.server.baeksstreetmapserver.store.entity.Menu;
import umc.server.baeksstreetmapserver.store.entity.Store;
import umc.server.baeksstreetmapserver.user.entity.User;

import javax.persistence.*;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class Review extends BaseEntity {


    @Id
    @Column(name = "idx")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idx;

    @Column(nullable = false)
    private Long likes;

    @Column(nullable = false)
    private Long changes;

    @Column(name = "review_text")
    private String text;

    @Enumerated(EnumType.STRING)
    @Column(name = "review_status", nullable = false)
    private Status status;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_idx", nullable = false)
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "menu_idx", nullable = false)
    private Menu menu;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "store_idx", nullable = false)
    private Store store;


}

