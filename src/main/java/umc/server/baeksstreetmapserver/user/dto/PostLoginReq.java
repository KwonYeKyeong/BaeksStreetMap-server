package umc.server.baeksstreetmapserver.user.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class PostLoginReq {
    
    private String loginId;
    private String password;
}
