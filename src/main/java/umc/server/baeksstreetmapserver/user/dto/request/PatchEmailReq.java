package umc.server.baeksstreetmapserver.user.dto.request;

import lombok.Getter;

import javax.validation.constraints.NotEmpty;

@Getter
public class PatchEmailReq {

	@NotEmpty
	private String email;

}
