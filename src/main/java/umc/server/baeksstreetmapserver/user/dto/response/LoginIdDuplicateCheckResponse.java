package umc.server.baeksstreetmapserver.user.dto.response;

import lombok.Getter;

@Getter
public class LoginIdDuplicateCheckResponse {

	private static final String DUPLICATED_MESSAGE = "중복된 아이디입니다.";
	private static final String NOT_DUPLICATED_MESSAGE = "사용 가능한 아이디입니다.";

	private String result;

	private LoginIdDuplicateCheckResponse(String result) {
		this.result = result;
	}

	public static LoginIdDuplicateCheckResponse of(boolean duplicatedOrNot) {
		return new LoginIdDuplicateCheckResponse(duplicatedOrNot == true ? DUPLICATED_MESSAGE : NOT_DUPLICATED_MESSAGE);
	}

}
