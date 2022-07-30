package umc.server.baeksstreetmapserver.store.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import umc.server.baeksstreetmapserver.store.dto.response.StoreInBoundaryResponse;
import umc.server.baeksstreetmapserver.store.service.StoreService;

import java.util.List;

@RequiredArgsConstructor
@RequestMapping(value = "/stores")
@RestController
public class StoreController {

	private final StoreService storeService;

	@ResponseBody
	@GetMapping()
	public ResponseEntity<List<StoreInBoundaryResponse>> getAllInBoundary(@RequestParam List<Double> latitudes, @RequestParam List<Double> longitudes) {
		List<StoreInBoundaryResponse> response = storeService.getAllInBoundary(latitudes, longitudes);
		return ResponseEntity.ok(response);
	}

}