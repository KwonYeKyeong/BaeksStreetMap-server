package umc.server.baeksstreetmapserver.review.entity;

import lombok.*;
import umc.server.baeksstreetmapserver.common.BaseEntity;
import umc.server.baeksstreetmapserver.common.Status;
import umc.server.baeksstreetmapserver.review.converter.BooleanToYNConverter;
import umc.server.baeksstreetmapserver.store.entity.Menu;
import umc.server.baeksstreetmapserver.store.entity.Store;
import umc.server.baeksstreetmapserver.user.entity.User;

import javax.persistence.*;
import java.util.Objects;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity

public class Review extends BaseEntity {

    @Id
    @Column(nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idx;

    @Convert(converter = BooleanToYNConverter.class)
    @Column(nullable = false)
    private boolean likes;


    @Column(nullable = false)
    private Long changes;

    @Lob
    private String text;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
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



    /* likes, changes, text, menu_idx만 수정 가능*/
    public void modify(Boolean likes, Long changes, String text, Menu menu){
        this.likes = likes;
        this.changes = changes;
        this.text = text;
        this.menu = menu;
    }


    public void delete() {
        this.status = Status.INACTIVE;
    }

    @Builder
    public Review(boolean likes, Long changes, String text, Menu menu, Status status, Store store, User user) {
        this.likes = likes;
        this.changes = changes;
        this.text = text;
        this.menu = menu;
        this.status = status;
        this.store = store;
        this.user = user;
    }

    public boolean isWrittenBy(Long userIdx) {
        return Objects.equals(this.user.getIdx(), userIdx);
    }

}

