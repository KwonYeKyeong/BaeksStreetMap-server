package umc.server.baeksstreetmapserver.store.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import umc.server.baeksstreetmapserver.store.entity.Menu;
import umc.server.baeksstreetmapserver.store.entity.Store;

import java.util.List;

public interface MenuRepository extends JpaRepository<Menu, Long> {

	Menu findFirstByStore(Store store);

	List<Menu> findByStore(Store store);

}
