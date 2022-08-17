package umc.server.baeksstreetmapserver.store.dto.response;

import lombok.Getter;
import umc.server.baeksstreetmapserver.store.entity.Menu;
import umc.server.baeksstreetmapserver.store.entity.Store;

import java.util.List;
import java.util.stream.Collectors;

@Getter
public class StoreSearchResponse {

	private Long storeIdx;
	private String name;
	private int like;
	private String image;
	private List<String> menu;
	private double latitude;
	private double longitude;

	private StoreSearchResponse(Long storeIdx, String name, int like, String image, List<String> menu, double latitude, double longitude) {
		this.storeIdx = storeIdx;
		this.name = name;
		this.like = like;
		this.image = image;
		this.menu = menu;
		this.latitude = latitude;
		this.longitude = longitude;
	}

	public static StoreSearchResponse of(Store store, int like, List<Menu> menu) {
		List<String> menuNameList = menu.stream().map(Menu::getName).collect(Collectors.toList());
		return new StoreSearchResponse(store.getIdx(), store.getName(), like, store.getImage(), menuNameList, store.getLatitude(), store.getLongitude());
	}

}
