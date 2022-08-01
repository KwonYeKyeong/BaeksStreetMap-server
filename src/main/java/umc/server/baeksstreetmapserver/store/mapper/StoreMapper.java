package umc.server.baeksstreetmapserver.store.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import umc.server.baeksstreetmapserver.store.dto.response.StoreInBoundaryResponse;
import umc.server.baeksstreetmapserver.store.entity.Store;

@Mapper
public interface StoreMapper {

	@Mapping(target = "storeIdx", source = "store.idx")
	StoreInBoundaryResponse toResponse(Store store);

}