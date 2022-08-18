package umc.server.baeksstreetmapserver.user.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import umc.server.baeksstreetmapserver.user.entity.User;

public interface UserRepository extends JpaRepository<User, Long> {

	boolean existsByLoginId(String loginId);

	boolean existsByEmail(String email);

	boolean existsByNickname(String nickname);
    public void save(User user) {
        em.persist(user);
    }

    public User findOne(Long id) {
        return em.find(User.class, id);
    }

    public User findByLoginId(String loginId) {
        return em.find(User.class, loginId);
    }

    public User checkEmail(String email) {
        return em.find(User.class, email);
    }

}
