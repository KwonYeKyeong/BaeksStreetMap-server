package umc.server.baeksstreetmapserver.review.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import umc.server.baeksstreetmapserver.review.entity.Report;
import umc.server.baeksstreetmapserver.review.entity.Review;

import java.util.List;

public interface ReportRepository extends JpaRepository<Report, Long> {
    List<Report> findByReview(Review review);
}
