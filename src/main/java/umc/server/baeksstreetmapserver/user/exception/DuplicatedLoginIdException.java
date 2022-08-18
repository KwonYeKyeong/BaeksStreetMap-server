package umc.server.baeksstreetmapserver.user.exception;

import umc.server.baeksstreetmapserver.common.error.exception.BusinessException;
import umc.server.baeksstreetmapserver.common.error.exception.ErrorCode;

import static umc.server.baeksstreetmapserver.common.error.exception.ErrorCode.LOGIN_ID_DUPLICATION;

public class DuplicatedLoginIdException extends BusinessException {

	public DuplicatedLoginIdException(String message, ErrorCode errorCode) {
		super(message, LOGIN_ID_DUPLICATION);
	}

}
