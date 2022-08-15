package umc.server.baeksstreetmapserver.store.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import umc.server.baeksstreetmapserver.store.entity.Menu;
import umc.server.baeksstreetmapserver.store.entity.Store;

public interface MenuRepository extends JpaRepository<Menu, Long> {

	Menu findFirstByStore(Store store);

}
