package umc.server.baeksstreetmapserver.user.dto.response;

import lombok.Getter;

@Getter
public class NicknameDuplicateCheckResponse {

	private static final String DUPLICATED_MESSAGE = "중복된 닉네임입니다.";
	private static final String NOT_DUPLICATED_MESSAGE = "사용 가능한 닉네임입니다.";

	private String result;

	private NicknameDuplicateCheckResponse(String result) {
		this.result = result;
	}

	public static NicknameDuplicateCheckResponse of(boolean duplicatedOrNot) {
		return new NicknameDuplicateCheckResponse(duplicatedOrNot == true ? DUPLICATED_MESSAGE : NOT_DUPLICATED_MESSAGE);
	}

}
