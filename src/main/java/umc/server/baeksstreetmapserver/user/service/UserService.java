package umc.server.baeksstreetmapserver.user.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import umc.server.baeksstreetmapserver.user.repository.UserRepository;

@Transactional(readOnly = true)
@RequiredArgsConstructor
@Service
public class UserService {

	private final UserRepository userRepository;

	public boolean loginIdDuplicateCheck(String loginId){
		return userRepository.existsByLoginId(loginId);
	}

}
