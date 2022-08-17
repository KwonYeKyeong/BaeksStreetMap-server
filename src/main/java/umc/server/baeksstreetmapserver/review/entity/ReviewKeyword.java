package umc.server.baeksstreetmapserver.review.entity;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import umc.server.baeksstreetmapserver.store.entity.Menu;

import javax.persistence.*;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Entity
public class ReviewKeyword {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "review_idx", nullable = false)
	private Review review;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "keyword_idx", nullable = false)
	private Keyword keyword;

	@Builder
	public ReviewKeyword(Review review, Keyword keyword) {
		this.review = review;
		this.keyword = keyword;
	}

}
