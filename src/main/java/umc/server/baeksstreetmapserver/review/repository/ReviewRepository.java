package umc.server.baeksstreetmapserver.review.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import umc.server.baeksstreetmapserver.review.entity.Review;
import umc.server.baeksstreetmapserver.store.entity.Store;

import java.util.List;

public interface ReviewRepository extends JpaRepository<Review, Long> {

	Long countByStore(Store store);

	@Query("SELECT COUNT(*) FROM Review r WHERE r.store = :store AND r.likes = 'Y'")
	Long countByStoreAndLikes(Store store);

	@Query(value = "SELECT r.changes\n" +
		"FROM review r JOIN store s ON r.store_idx = s.idx\n" +
		"WHERE s.idx = :storeIdx\n" +
		"GROUP BY r.changes HAVING COUNT(r.changes)\n" +
		"ORDER BY COUNT(r.changes) DESC LIMIT 1", nativeQuery = true)
	Long getChangesWithTheMost(Long storeIdx);

	@Query(value = "SELECT r.menu_idx\n" +
		"FROM review r JOIN store s ON r.store_idx = s.idx\n" +
		"WHERE s.idx = :storeIdx\n" +
		"GROUP BY r.menu_idx HAVING COUNT(r.menu_idx)\n" +
		"ORDER BY COUNT(r.menu_idx) DESC LIMIT 1", nativeQuery = true)
	Long getBestMenuIdx(Long storeIdx);

	List<Review> findByStoreOrderByCreatedAtDesc(Store store);

}
