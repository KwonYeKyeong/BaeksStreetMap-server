package umc.server.baeksstreetmapserver.store.dto.response;

import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Getter
public class StoreBriefInfoResponse {

	private Long storeIdx;
	private String name;
	private Long change;
	private Integer like;
	private String bestMenu;
	private List<Long> keyword;

	public StoreBriefInfoResponse(Long storeIdx, String name, Long change, Integer like, String bestMenu, List<Long> keyword) {
		this.storeIdx = storeIdx;
		this.name = name;
		this.change = (change == null ? 1L : change);
		this.like = like;
		this.bestMenu = bestMenu;
		this.keyword = keyword.size() > 3 ? new ArrayList<>() {
			{
				keyword.get(0);
				keyword.get(1);
				keyword.get(2);
			}
		} : keyword;
	}

}
