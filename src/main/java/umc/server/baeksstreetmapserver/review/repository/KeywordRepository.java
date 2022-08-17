package umc.server.baeksstreetmapserver.review.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import umc.server.baeksstreetmapserver.review.entity.Keyword;

import java.util.List;

public interface KeywordRepository extends JpaRepository<Keyword, Long> {

	@Query("SELECT k FROM Keyword k WHERE k.idx in :idxList")
	List<Keyword> findIn(List<Long> idxList);

}
