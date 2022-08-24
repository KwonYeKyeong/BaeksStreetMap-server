package umc.server.baeksstreetmapserver.store.dto.response;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class StoreInBoundaryResponse {

	private Long storeIdx;
	private String name;
	private double latitude;
	private double longitude;

}
