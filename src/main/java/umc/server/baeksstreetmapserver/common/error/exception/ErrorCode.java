package umc.server.baeksstreetmapserver.common.error.exception;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;

@Getter
@JsonFormat(shape = JsonFormat.Shape.OBJECT)
public enum ErrorCode {

	// Common
	INVALID_INPUT_VALUE(400, "Invalid Input Value"),
	METHOD_NOT_ALLOWED(405, "Invalid Input Value"),
	ENTITY_NOT_FOUND(400, "Entity Not Found"),
	INTERNAL_SERVER_ERROR(500, "Server Error"),
	INVALID_TYPE_VALUE(400, " Invalid Type Value"),
	HANDLE_ACCESS_DENIED(403, "Access is Denied"),

	// User
	LOGIN_ID_DUPLICATION(400, "Login Id is Duplicated"),
	NICKNAME_DUPLICATION(400, "Nickname is Duplicated"),
	EMAIL_DUPLICATION(400, "Email is Duplication"),
	LOGIN_INPUT_INVALID(400, "Login input is invalid");

	private final String message;
	private int status;

	ErrorCode(final int status, final String message) {
		this.status = status;
		this.message = message;
	}

}