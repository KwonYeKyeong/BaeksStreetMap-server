package umc.server.baeksstreetmapserver.user.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import umc.server.baeksstreetmapserver.user.dto.request.*;
import umc.server.baeksstreetmapserver.user.dto.response.*;
import umc.server.baeksstreetmapserver.user.repository.UserRepository;
import umc.server.baeksstreetmapserver.common.Status;
import umc.server.baeksstreetmapserver.user.entity.User;
import umc.server.baeksstreetmapserver.utils.JwtService;
import umc.server.baeksstreetmapserver.utils.SHA256;

@Transactional(readOnly = true)
@RequiredArgsConstructor
@Service
public class UserService {

    private final UserRepository userRepository;
    private final JwtService jwtService;

    @Transactional
    public PostUserRes createUser(PostUserReq postUserReq) throws Exception {
        // 이메일 중복 확인
//        if(checkEmail(postUserReq.getEmail()) == 1){
//            throw new Exception("POST_USERS_EXISTS_EMAIL");
//        }

        String pwd;
        try{
            //암호화
            pwd = new SHA256().encrypt(postUserReq.getPassword());
        } catch (Exception ignored) {

            throw new Exception("비밀번호 암호화 오류");
        }
        try{
            User user = new User();
            user.setLoginId(postUserReq.getLoginId());
            user.setEmail(postUserReq.getEmail());
            user.setPassword(pwd);
            user.setNickname(postUserReq.getNickname());
            user.setStatus(Status.ACTIVE);

            userRepository.save(user);

            String result = "회원 가입을 성공했습니다";

            return new PostUserRes(result);
        } catch (Exception exception) {
            throw new Exception("데이터베이스 오류");
        }
    }

    public int checkEmail(String email) throws Exception{
        try{
            User user = userRepository.findByEmail(email);
            if (user != null){
                return 1;
            }
            else {
                return 0;
            }
        } catch (Exception exception){
            throw new Exception("데이터베이스 오류");
        }
    }


    //회원가입 비밀번호 암호화 로직 필요함
    public PostLoginRes logIn(PostLoginReq postLoginReq) throws Exception{

        User user = userRepository.findByLoginId(postLoginReq.getLoginId());
        String encryptPwd;

        //status 밸리데이션
        if (user.getStatus() == Status.INACTIVE) {
            throw new Exception("비활성화된 회원입니다.");
        }

        try {
            encryptPwd = new SHA256().encrypt(postLoginReq.getPassword());
        }
        catch (Exception exception) {
            throw new Exception("비밀번호 암호화 오류");
        }

        if (user.getPassword().equals(encryptPwd)) {

            Long userIdx = user.getIdx();
            String jwt = jwtService.createJwt(userIdx);
            String nickName = user.getNickname();
            //jwt는 헤더에 넘겨야하는건데 이렇게 넘겨도 되나요?
            return new PostLoginRes(userIdx, jwt, nickName);
        }
        else
            throw new Exception("비밀번호가 일치하지 않습니다.");
    }


    @Transactional
    public PatchPasswordRes modifyUserPassword(Long userIdx, PatchPasswordReq patchPasswordReq) throws Exception {
        try{
            //기존 비밀번호하고 일치하는지 확인
            User user = userRepository.findById(userIdx).get();
            if (user.getPassword() != patchPasswordReq.getPassword()) {
                throw new Exception("비밀번호가 일치하지 않습니다.");
            }
            user.setPassword(patchPasswordReq.getNewPassword());
            return new PatchPasswordRes("비밀번호 변경을 성공했습니다");

        } catch(Exception exception){
            throw new Exception("DATABASE_ERROR");
        }
    }

    @Transactional
    public PatchNicknameRes modifyUserNickname(Long userIdx, PatchNicknameReq patchNicknameReq) throws Exception {
        try{
            if (nicknameDuplicateCheck(patchNicknameReq.getNickname())) {
                return new PatchNicknameRes("닉네임 변경이 불가능합니다.(닉네임 중복)");
            }

            User user = userRepository.findById(userIdx).get();
            user.setNickname(patchNicknameReq.getNickname());

            return new PatchNicknameRes("닉네임 변경을 성공했습니다");

        } catch(Exception exception){
            throw new Exception("DATABASE_ERROR");
        }
    }

    @Transactional
    public PatchEmailRes modifyUserEmail(Long userIdx, String email){
        if (emailDuplicateCheck(email)) {
            return new PatchEmailRes("이메일 변경이 불가능합니다.(이메일 중복)");
        }

        User user = userRepository.findById(userIdx).get();
        user.setEmail(email);

        return new PatchEmailRes("이메일 변경을 성공했습니다.");
    }

    @Transactional
    public PatchUserImageRes modifyUserImage(Long userIdx, PatchUserImageReq patchUserImageReq) throws Exception {
        try{
            User user = userRepository.findById(userIdx).get();
            user.setImage(patchUserImageReq.getImage());

            return new PatchUserImageRes("프로필 사진 변경을 성공했습니다");

        } catch(Exception exception){
            throw new Exception("DATABASE_ERROR");
        }
    }

    @Transactional
    public PatchUserStatusRes modifyUserStatus(Long userIdx) throws Exception {
        try{
            User user = userRepository.findById(userIdx).get();
            user.setStatus(Status.INACTIVE);
            return new PatchUserStatusRes("회원 탈퇴를 성공했습니다");

        } catch(Exception exception){
            throw new Exception("DATABASE_ERROR");
        }
    }

    public boolean loginIdDuplicateCheck(String loginId) {
		return userRepository.existsByLoginId(loginId);
	}

	public boolean emailDuplicateCheck(String email) {
		return userRepository.existsByEmail(email);
	}

	public boolean nicknameDuplicateCheck(String nickname) {
		return userRepository.existsByNickname(nickname);
	}
    
}
