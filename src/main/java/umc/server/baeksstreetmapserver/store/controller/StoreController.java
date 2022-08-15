package umc.server.baeksstreetmapserver.store.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import umc.server.baeksstreetmapserver.store.dto.response.StoreBriefInfoResponse;
import umc.server.baeksstreetmapserver.store.dto.response.StoreDetailInfoResponse;
import umc.server.baeksstreetmapserver.store.dto.response.StoreInBoundaryResponse;
import umc.server.baeksstreetmapserver.store.dto.response.StoreSearchResponse;
import umc.server.baeksstreetmapserver.store.entity.Region;
import umc.server.baeksstreetmapserver.store.service.StoreService;

import javax.validation.Valid;
import javax.validation.constraints.Size;
import java.util.List;

@Validated
@RequiredArgsConstructor
@RequestMapping(value = "/stores")
@RestController
public class StoreController {

	private final StoreService storeService;

	@GetMapping()
	public ResponseEntity<List<StoreInBoundaryResponse>> getAllInBoundary(@RequestParam @Size(min = 2, max = 2) List<Double> latitudes, @RequestParam @Size(min = 2, max = 2) List<Double> longitudes) {
		List<StoreInBoundaryResponse> response = storeService.getAllInBoundary(latitudes, longitudes);
		return ResponseEntity.ok(response);
	}

	@GetMapping("/{storeIdx}")
	public ResponseEntity<StoreBriefInfoResponse> getBriefInfo(@PathVariable Long storeIdx) {
		StoreBriefInfoResponse response = storeService.getBriefInfo(storeIdx);
		return ResponseEntity.ok(response);
	}

	@GetMapping("/{storeIdx}/detail")
	public ResponseEntity<StoreDetailInfoResponse> getDetailInfo(@PathVariable Long storeIdx) {
		StoreDetailInfoResponse response = storeService.getDetailInfo(storeIdx);
		return ResponseEntity.ok(response);
	}

	@GetMapping("/search")
	public ResponseEntity<List<StoreSearchResponse>> search(
		@RequestParam(required = false) String query, @RequestParam(required = false) List<Long> keyword, @Valid @RequestParam(required = false) List<Region> region){
		List<StoreSearchResponse> response = storeService.search(query, keyword, region);
		return ResponseEntity.ok(response);
	}

}