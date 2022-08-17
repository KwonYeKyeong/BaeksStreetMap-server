package umc.server.baeksstreetmapserver.review.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import umc.server.baeksstreetmapserver.review.entity.Keyword;
import umc.server.baeksstreetmapserver.review.entity.Review;
import umc.server.baeksstreetmapserver.review.entity.ReviewKeyword;

import java.util.List;

public interface ReviewKeywordRepository extends JpaRepository<ReviewKeyword, Long> {

	@Query(value = "SELECT rk.keyword.idx from ReviewKeyword rk WHERE rk.review in :reviewList")
	List<Long> findKeywordsIn(List<Review> reviewList);

	List<Keyword> findByReview(Review review);

	List<ReviewKeyword> findKeywordByReview(Review review);

}
