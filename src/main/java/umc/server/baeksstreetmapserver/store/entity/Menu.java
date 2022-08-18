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
public class Menu extends BaseEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idx;

	@Column(nullable = false)
	private String name;

	private String image;

	@Enumerated(EnumType.STRING)
	@Column(nullable = false)
	private Status status;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "store_idx", nullable = false)
	private Store store;

	@Builder
	public Menu(String name, String image, Status status, Store store) {
		this.name = name;
		this.image = image;
		this.status = status;
		this.store = store;
	}

}
