package umc.server.baeksstreetmapserver.store.service;

import lombok.RequiredArgsConstructor;
import org.mapstruct.factory.Mappers;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import umc.server.baeksstreetmapserver.store.dto.response.StoreInBoundaryResponse;
import umc.server.baeksstreetmapserver.store.entity.Store;
import umc.server.baeksstreetmapserver.store.mapper.StoreMapper;
import umc.server.baeksstreetmapserver.store.repository.StoreRepository;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Transactional(readOnly = true)
@RequiredArgsConstructor
@Service
public class StoreService {

	private final StoreRepository storeRepository;

	private final StoreMapper storeMapper = Mappers.getMapper(StoreMapper.class);

	public List<StoreInBoundaryResponse> getAllInBoundary(List<Double> latitudes, List<Double> longitudes){
		latitudes.sort(Comparator.naturalOrder());
		longitudes.sort(Comparator.naturalOrder());

		List<Store> storesInBoundary = storeRepository.findByBoundary(longitudes.get(0), longitudes.get(1), longitudes.get(0), latitudes.get(1));

		return storesInBoundary.stream()
			.map(storeMapper::toResponse)
			.collect(Collectors.toList());
	}

}
