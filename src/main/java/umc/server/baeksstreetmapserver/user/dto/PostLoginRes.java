package umc.server.baeksstreetmapserver.user.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class PostLoginRes {

    private Long userIdx;
    private String jwt;
    private String nickName;
}
