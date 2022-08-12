package umc.server.baeksstreetmapserver.user.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class PostUserReq {

    private String loginId;
    private String nickName;
    private String email;
    private String password;
}