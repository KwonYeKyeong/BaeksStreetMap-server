package umc.server.baeksstreetmapserver.store.entity;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import umc.server.baeksstreetmapserver.common.BaseEntity;
import umc.server.baeksstreetmapserver.common.Status;

import javax.persistence.*;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class Store extends BaseEntity {

	private static final int MAX_NAME_LENGTH = 100;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idx;

	@Column(nullable = false, length = MAX_NAME_LENGTH)
	private String name;

	@Column(nullable = false)
	private String address;

	@Column(nullable = false)
	private double latitude;

	@Column(nullable = false)
	private double longitude;

	@Lob
	private String introduce;

	@Enumerated(EnumType.STRING)
	@Column(nullable = false)
	private Region region;

	@Enumerated(EnumType.STRING)
	@Column(nullable = false)
	private Status status;

	private String image;

	@Column(nullable = false)
	private String video;

	@Builder
	public Store(String name, String address, double latitude, double longitude,
				 String introduce, Region region, Status status, String image, String video) {
		this.name = name;
		this.address = address;
		this.latitude = latitude;
		this.longitude = longitude;
		this.introduce = introduce;
		this.region = region;
		this.status = status;
		this.image = image;
		this.video = video;
	}

}
