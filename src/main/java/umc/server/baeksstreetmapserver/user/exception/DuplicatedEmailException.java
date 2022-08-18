package umc.server.baeksstreetmapserver.user.exception;

import umc.server.baeksstreetmapserver.common.error.exception.BusinessException;
import umc.server.baeksstreetmapserver.common.error.exception.ErrorCode;

import static umc.server.baeksstreetmapserver.common.error.exception.ErrorCode.EMAIL_DUPLICATION;

public class DuplicatedEmailException extends BusinessException {

	public DuplicatedEmailException(String message, ErrorCode errorCode) {
		super(message, EMAIL_DUPLICATION);
	}

}
