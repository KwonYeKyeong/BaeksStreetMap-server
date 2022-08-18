package umc.server.baeksstreetmapserver.user.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import umc.server.baeksstreetmapserver.user.entity.User;

public interface UserRepository extends JpaRepository<User, Long> {

	boolean existsByLoginId(String loginId);

	boolean existsByEmail(String email);

	boolean existsByNickname(String nickname);

}
