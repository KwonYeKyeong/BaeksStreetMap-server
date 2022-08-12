package umc.server.baeksstreetmapserver.user.entity;

import lombok.*;
import umc.server.baeksstreetmapserver.common.BaseEntity;
import umc.server.baeksstreetmapserver.common.Status;

import javax.persistence.*;

@Getter
@Setter
@ToString
// NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class User extends BaseEntity {

    private static final int MAX_NAME_LENGTH = 30;
    private static final int MAX_PASSWORD_LENGTH = 30;
    private static final int MAX_ID_LENGTH = 20;

    @Id
    @Column(name = "idx")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idx;

    @Column(name = "email", nullable = false, unique = true)
    private String email;

    @Column(name = "login_id", nullable = false, length = MAX_ID_LENGTH,unique = true)
    private String loginId;

    @Column(name = "password", nullable = false, length = MAX_PASSWORD_LENGTH)
    private String password;

    @Column(name = "nickname", nullable = false, length = MAX_NAME_LENGTH, unique = true)
    private String nickname;

    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false)
    private Status status;

    @Column(name = "profile_image")
    private String image;

}
