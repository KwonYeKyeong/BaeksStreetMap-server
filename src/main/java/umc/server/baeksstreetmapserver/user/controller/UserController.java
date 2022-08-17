package umc.server.baeksstreetmapserver.user.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import umc.server.baeksstreetmapserver.user.dto.response.EmailDuplicateCheckResponse;
import umc.server.baeksstreetmapserver.user.dto.response.LoginIdDuplicateCheckResponse;
import umc.server.baeksstreetmapserver.user.service.UserService;

@RequiredArgsConstructor
@RequestMapping(value = "/users")
@RestController
public class UserController {

	private final UserService userService;

	@GetMapping("/loginId/{loginId}")
	public ResponseEntity<LoginIdDuplicateCheckResponse> loginIdDuplicateCheck(@PathVariable String loginId) {
		LoginIdDuplicateCheckResponse response
			= LoginIdDuplicateCheckResponse.of(userService.loginIdDuplicateCheck(loginId));
		return ResponseEntity.ok(response);
	}

	@GetMapping("/email/{email}")
	public ResponseEntity<EmailDuplicateCheckResponse> emailDuplicateCheck(@PathVariable String email) {
		EmailDuplicateCheckResponse response
			= EmailDuplicateCheckResponse.of(userService.emailDuplicateCheck(email));
		return ResponseEntity.ok(response);
	}

}
