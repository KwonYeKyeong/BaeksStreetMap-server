package umc.server.baeksstreetmapserver.user.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@AllArgsConstructor
public class PostLoginReq {
    
    private String loginId;
    private String password;
}
