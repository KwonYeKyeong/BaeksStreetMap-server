package umc.server.baeksstreetmapserver.user.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotEmpty;

@Getter
@Setter
@AllArgsConstructor
public class PostUserReq {

	@Length(min = 5)
	private String loginId;
	@Length(min = 1, max = 10)
	private String nickname;
	@NotEmpty
	private String email;
	@Length(min = 4)
	private String password;

}