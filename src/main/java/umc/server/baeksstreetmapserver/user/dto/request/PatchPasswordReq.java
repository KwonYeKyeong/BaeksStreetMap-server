package umc.server.baeksstreetmapserver.user.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

@Getter
@AllArgsConstructor
public class PatchPasswordReq {

    private String password;

    @Length(min = 4)
    private String newPassword;

}
