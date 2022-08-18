package umc.server.baeksstreetmapserver.user.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

@Getter
@Setter
@AllArgsConstructor
public class PatchNicknameReq {

    @Length(min = 1, max = 10)
    private String nickname;

}
