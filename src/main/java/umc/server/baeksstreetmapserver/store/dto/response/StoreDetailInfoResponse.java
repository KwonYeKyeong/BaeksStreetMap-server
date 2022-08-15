package umc.server.baeksstreetmapserver.store.dto.response;

import lombok.Getter;
import umc.server.baeksstreetmapserver.review.dto.ReviewDto;
import umc.server.baeksstreetmapserver.review.entity.Keyword;
import umc.server.baeksstreetmapserver.review.entity.Review;
import umc.server.baeksstreetmapserver.review.entity.ReviewImg;
import umc.server.baeksstreetmapserver.store.entity.Menu;
import umc.server.baeksstreetmapserver.store.entity.Store;

import java.util.List;
import java.util.stream.Collectors;

@Getter
public class StoreDetailInfoResponse {

	private Long storeIdx;
	private String name;
	private String introduce;
	private int like;
	private String image;
	private String video;
	private List<String> menu;
	private List<ReviewDto> reviews;

	private StoreDetailInfoResponse(Long storeIdx, String name, String introduce, int like, String image, String video, List<String> menu, List<ReviewDto> reviews) {
		this.storeIdx = storeIdx;
		this.name = name;
		this.introduce = introduce;
		this.like = like;
		this.image = image;
		this.video = video;
		this.menu = menu;
		this.reviews = reviews;
	}

	public static StoreDetailInfoResponse of(Store store, int like, List<Menu> menuList, List<ReviewDto> reviewList){
		List<String> menuNameList = menuList.stream().map(Menu::getName).collect(Collectors.toList());

		return new StoreDetailInfoResponse(store.getIdx(), store.getName(), store.getIntroduce(), like,
			store.getImage(), store.getVideo(), menuNameList, reviewList);
	}

}
