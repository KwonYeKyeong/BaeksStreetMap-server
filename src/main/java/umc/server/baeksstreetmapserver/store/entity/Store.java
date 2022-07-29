package umc.server.baeksstreetmapserver.store.entity;

import lombok.AccessLevel;
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

	@Lob
	private String introduce;

	@Enumerated(EnumType.STRING)
	@Column(nullable = false)
	private Region region;

	@Enumerated(EnumType.STRING)
	@Column(nullable = false)
	private Status status;

	@Column(nullable = false)
	private String image;

	@Column(nullable = false)
	private String video;

}
