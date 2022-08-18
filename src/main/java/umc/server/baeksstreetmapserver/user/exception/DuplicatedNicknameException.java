package umc.server.baeksstreetmapserver.user.exception;

import umc.server.baeksstreetmapserver.common.error.exception.BusinessException;
import umc.server.baeksstreetmapserver.common.error.exception.ErrorCode;

public class DuplicatedNicknameException extends BusinessException {

	public DuplicatedNicknameException(String message, ErrorCode errorCode) {
		super(message, ErrorCode.NICKNAME_DUPLICATION);
	}

}
