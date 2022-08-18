package umc.server.baeksstreetmapserver.user.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import umc.server.baeksstreetmapserver.user.dto.request.*;
import umc.server.baeksstreetmapserver.user.dto.response.*;
import umc.server.baeksstreetmapserver.user.service.UserService;
import umc.server.baeksstreetmapserver.utils.JwtService;

import java.util.regex.Pattern;

import static umc.server.baeksstreetmapserver.utils.ValidationRegex.isRegexEmail;

import javax.validation.Valid;

@Validated
@RequiredArgsConstructor
@RequestMapping(value = "/users")
@RestController
public class UserController {
    private final UserService userService;
    private final JwtService jwtService;

    //회원가입
    @PostMapping("")
    public ResponseEntity<PostUserRes> createUser(@Valid @RequestBody PostUserReq postUserReq) {
        //아이디
        //숫자와 알파벳만 가능
        //4글자 이상만 가능
        boolean isRegexId = Pattern.matches("^[a-zA-Z0-9]*$", postUserReq.getLoginId());
        if(!isRegexId){
            //숫자와 소문자, 대문자로 입력해주세요
            return ResponseEntity.notFound().build();
        }

        //비밀번호

        //최소 8 자, 최소 하나의 문자, 하나의 숫자 및 하나의 특수 문자
        //^(?=.*[A-Za-z])(?=.*\d)(?=.*[@$!%*#?&])[A-Za-z\d@$!%*#?&]{8,}$"
        //"[^\"'\\{\\}\\[\\]/?.,;:|\\)\\(*~`!^\\-_+<>@#$%^\\\\=]";
        //한글 안되게 막기
        //"^(?=.*[A-Za-z])(?=.*\\d)(?=.*[@$!%*#?&])[A-Za-z\\d@$!%*#?&]{4,}$"
        //"^[a-zA-Z0-9^\"'\\{\\}\\[\\]/?.,;:|\\)\\(*~`!^\\-_+<>@#$%^\\\\=]*$"
        //password정규표현식
        boolean isRegexPassword = Pattern.matches("^[a-zA-Z0-9^\"'\\{\\}\\[\\]/?.,;:|\\)\\(*~`!^\\-_+<>@#$%^\\\\=]*$", postUserReq.getPassword());
        if(!isRegexId){
            //숫자와 소문자, 대문자, 특수문자로 입력해주세요(4글자)
            return ResponseEntity.notFound().build();
        }

        // 이메일 정규표현
        if(!isRegexEmail(postUserReq.getEmail())){
            return ResponseEntity.notFound().build();
        }

        try{
            PostUserRes postUserRes = userService.createUser(postUserReq);
            return new ResponseEntity<>(postUserRes, HttpStatus.OK);
        } catch(Exception exception){
            return ResponseEntity.notFound().build();
        }
    }

    //로그인
    @PostMapping("/login")
    public ResponseEntity<PostLoginRes> logIn(@RequestBody PostLoginReq postLoginReq) {
        try {
            //밸리데이션
            if(postLoginReq.getLoginId() == null) {
                return ResponseEntity.notFound().build();
            }

            if(postLoginReq.getPassword() == null) {
                return ResponseEntity.notFound().build();
            }
            PostLoginRes postLoginRes = userService.logIn(postLoginReq);
            return new ResponseEntity<>(postLoginRes, HttpStatus.OK);
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

    //비밀번호 변경
    //변경된 정보들도 형식에 맞는지 확인 필요
    @ResponseBody
    //PostLoginRes
    @PatchMapping("/{userIdx}/password")
    public ResponseEntity<PatchPasswordRes> modifyUserPassword(@PathVariable("userIdx") Long userIdx, @RequestBody PatchPasswordReq patchPasswordReq){
        try {

            Long userIdxByJwt = jwtService.getUserIdx();

            if(userIdx != userIdxByJwt){
                return ResponseEntity.notFound().build();
            }

            //패스워드 형식에 맞는지 확인
            //patchPasswordReq.getPassword();

            PatchPasswordRes patchPasswordRes = userService.modifyUserPassword(userIdx, patchPasswordReq);

            return new ResponseEntity<>(patchPasswordRes, HttpStatus.OK);
        } catch (Exception exception) {
            //return new ResponseEntity<>((exception.getStatus()));
            return ResponseEntity.notFound().build();
        }
    }

    //닉네임 변경
    //중복 로직 필요
    @ResponseBody
    @PatchMapping("/{userIdx}/nickname")
    public ResponseEntity<PatchNicknameRes> modifyUserNickname(@PathVariable("userIdx") Long userIdx, @RequestBody PatchNicknameReq patchNicknameReq){
        try {
            Long userIdxByJwt = jwtService.getUserIdx();
            if(userIdx != userIdxByJwt){
                //return new BaseResponse<>(INVALID_USER_JWT);
                return ResponseEntity.notFound().build();
            }

            if(patchNicknameReq.getNickname().length() < 1 || patchNicknameReq.getNickname().length() > 10){
                return ResponseEntity.notFound().build();
            }

            PatchNicknameRes patchNicknameRes = userService.modifyUserNickname(userIdx, patchNicknameReq);

            return new ResponseEntity<>(patchNicknameRes, HttpStatus.OK);
        } catch (Exception exception) {
            //return new ResponseEntity<>((exception.getStatus()));
            return ResponseEntity.notFound().build();
        }
    }

    //프로필 사진 변경
    @ResponseBody
    @PatchMapping("/{userIdx}/image")
    public ResponseEntity<PatchUserImageRes> modifyUserImage(@PathVariable("userIdx") Long userIdx, @RequestBody PatchUserImageReq patchUserImageReq){
        try {
            Long userIdxByJwt = jwtService.getUserIdx();
            if(userIdx != userIdxByJwt){
                return ResponseEntity.notFound().build();
            }

            PatchUserImageRes patchUserImageRes = userService.modifyUserImage(userIdx, patchUserImageReq);

            return new ResponseEntity<>(patchUserImageRes, HttpStatus.OK);
        } catch (Exception exception) {
            return ResponseEntity.notFound().build();
        }
    }

    //회원탈퇴
    @ResponseBody
    @PatchMapping("/{userIdx}/status")
    public ResponseEntity<PatchUserStatusRes> modifyUserStatus(@PathVariable("userIdx") Long userIdx){
        try {
            Long userIdxByJwt = jwtService.getUserIdx();

            if(userIdx != userIdxByJwt){
                return ResponseEntity.notFound().build();
            }

            PatchUserStatusRes patchUserStatusRes = userService.modifyUserStatus(userIdx);

            return new ResponseEntity<>(patchUserStatusRes, HttpStatus.OK);
        } catch (Exception exception) {
            return ResponseEntity.notFound().build();
        }
    }
    
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

	@GetMapping("/nickname/{nickname}")
	public ResponseEntity<NicknameDuplicateCheckResponse> nicknameDuplicateCheck(@PathVariable String nickname) {
		NicknameDuplicateCheckResponse response
			= NicknameDuplicateCheckResponse.of(userService.nicknameDuplicateCheck(nickname));
		return ResponseEntity.ok(response);
	}

}
