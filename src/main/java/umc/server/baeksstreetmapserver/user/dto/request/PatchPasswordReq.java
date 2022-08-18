package umc.server.baeksstreetmapserver.user.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class PatchPasswordReq {
    private String password;
    private String newPassword;
}
