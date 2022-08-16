package umc.server.baeksstreetmapserver.store.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import umc.server.baeksstreetmapserver.store.entity.Region;
import umc.server.baeksstreetmapserver.store.entity.Store;

import java.util.List;

public interface StoreRepository extends JpaRepository<Store, Long> {

	@Query(value = "SELECT s FROM Store s " +
		"WHERE (s.latitude BETWEEN :startLatitude AND :endLatitude) " +
		"AND (s.longitude BETWEEN :startLongitude AND :endLongitude)")
	List<Store> findByBoundary(double startLatitude, double endLatitude, double startLongitude, double endLongitude);

	List<Store> findDistinctByNameContains(String name);

	@Query("SELECT s FROM Store s WHERE s.region in :regionList")
	List<Store> findByRegionIn(List<Region> regionList);

}
