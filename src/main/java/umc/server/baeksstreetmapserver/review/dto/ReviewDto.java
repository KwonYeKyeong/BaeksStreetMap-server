package umc.server.baeksstreetmapserver.review.dto;

import lombok.Getter;
import umc.server.baeksstreetmapserver.review.entity.Keyword;
import umc.server.baeksstreetmapserver.review.entity.Review;
import umc.server.baeksstreetmapserver.review.entity.ReviewImg;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Getter
public class ReviewDto {

	private Long reviewIdx;
	private Long userIdx;
	private String name;
	private String profileImage;
	private List<Long> keyword;
	private String text;
	private LocalDateTime createdAt;
	private LocalDateTime updatedAt;

	private ReviewDto(Long reviewIdx, Long userIdx, String name, String profileImage, List<Long> keyword, String text, LocalDateTime createdAt, LocalDateTime updatedAt) {
		this.reviewIdx = reviewIdx;
		this.userIdx = userIdx;
		this.name = name;
		this.profileImage = profileImage;
		this.keyword = keyword;
		this.text = text;
		this.createdAt = createdAt;
		this.updatedAt = updatedAt;
	}

	public static ReviewDto of(Review review, List<Keyword> keywordList){
		List<Long> keywordIdxList = keywordList.stream().map(Keyword::getIdx).collect(Collectors.toList());

		return new ReviewDto(review.getIdx(), review.getUser().getIdx(), review.getUser().getNickname(), review.getUser().getImage(),
			keywordIdxList, review.getText(), review.getCreatedAt(), review.getUpdatedAt());
	}
}
