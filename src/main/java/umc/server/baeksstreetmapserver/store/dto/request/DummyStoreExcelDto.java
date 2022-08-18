package umc.server.baeksstreetmapserver.store.dto.request;

import lombok.Getter;
import lombok.Setter;
import umc.server.baeksstreetmapserver.common.Status;
import umc.server.baeksstreetmapserver.store.entity.Region;
import umc.server.baeksstreetmapserver.store.entity.Store;

@Setter
@Getter
public class DummyStoreExcelDto {

	private String name;
	private String address;
	private double latitude;
	private double longitude;
	private String introduce;
	private String region;
	private Status status;
	private String image;
	private String video;

	public static Store toEntity(DummyStoreExcelDto requestDto){
		return Store.builder()
			.name(requestDto.getName())
			.address(requestDto.getAddress())
			.latitude(requestDto.getLatitude())
			.longitude(requestDto.getLongitude())
			.introduce(requestDto.getIntroduce())
			.region(Region.valueOf(requestDto.getRegion()))
			.status(Status.ACTIVE)
			.image(requestDto.getImage())
			.video(requestDto.getVideo()).build();
	}

}
