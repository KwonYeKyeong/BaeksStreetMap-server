package umc.server.baeksstreetmapserver.user.dto.response;

import lombok.Getter;

@Getter
public class EmailDuplicateCheckResponse {

	private static final String DUPLICATED_MESSAGE = "중복된 이메일입니다.";
	private static final String NOT_DUPLICATED_MESSAGE = "사용 가능한 이메일입니다.";

	private String result;

	private EmailDuplicateCheckResponse(String result) {
		this.result = result;
	}

	public static EmailDuplicateCheckResponse of(boolean duplicatedOrNot) {
		return new EmailDuplicateCheckResponse(duplicatedOrNot == true ? DUPLICATED_MESSAGE : NOT_DUPLICATED_MESSAGE);
	}

}
