package umc.server.baeksstreetmapserver.review.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import umc.server.baeksstreetmapserver.review.entity.Keyword;

public interface KeywordRepository extends JpaRepository<Keyword, Long> {
}
