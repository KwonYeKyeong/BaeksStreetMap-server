package umc.server.baeksstreetmapserver.store.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import umc.server.baeksstreetmapserver.store.entity.Store;

public interface StoreRepository extends JpaRepository<Store, Long> {
}
