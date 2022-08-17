package umc.server.baeksstreetmapserver.store.service;

import lombok.RequiredArgsConstructor;
import org.mapstruct.factory.Mappers;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import umc.server.baeksstreetmapserver.review.dto.ReviewDto;
import umc.server.baeksstreetmapserver.review.entity.Keyword;
import umc.server.baeksstreetmapserver.review.entity.Review;
import umc.server.baeksstreetmapserver.review.repository.KeywordRepository;
import umc.server.baeksstreetmapserver.review.repository.ReviewKeywordRepository;
import umc.server.baeksstreetmapserver.review.repository.ReviewRepository;
import umc.server.baeksstreetmapserver.store.dto.response.StoreBriefInfoResponse;
import umc.server.baeksstreetmapserver.store.dto.response.StoreDetailInfoResponse;
import umc.server.baeksstreetmapserver.store.dto.response.StoreInBoundaryResponse;
import umc.server.baeksstreetmapserver.store.dto.response.StoreSearchResponse;
import umc.server.baeksstreetmapserver.store.entity.Menu;
import umc.server.baeksstreetmapserver.store.entity.Region;
import umc.server.baeksstreetmapserver.store.entity.Store;
import umc.server.baeksstreetmapserver.store.exception.StoreNotFoundException;
import umc.server.baeksstreetmapserver.store.mapper.StoreMapper;
import umc.server.baeksstreetmapserver.store.repository.MenuRepository;
import umc.server.baeksstreetmapserver.store.repository.StoreRepository;

import java.util.*;
import java.util.stream.Collectors;

@Transactional(readOnly = true)
@RequiredArgsConstructor
@Service
public class StoreService {

	private final StoreRepository storeRepository;
	private final ReviewRepository reviewRepository;
	private final MenuRepository menuRepository;
	private final ReviewKeywordRepository reviewKeywordRepository;
	private final KeywordRepository keywordRepository;

	private final StoreMapper storeMapper = Mappers.getMapper(StoreMapper.class);

	public List<StoreInBoundaryResponse> getAllInBoundary(List<Double> latitudes, List<Double> longitudes) {
		latitudes.sort(Comparator.naturalOrder());
		longitudes.sort(Comparator.naturalOrder());

		List<Store> storesInBoundary = storeRepository.findByBoundary(longitudes.get(0), longitudes.get(1), longitudes.get(0), latitudes.get(1));

		return storesInBoundary.stream()
			.map(storeMapper::toResponse)
			.collect(Collectors.toList());
	}

	public StoreBriefInfoResponse getBriefInfo(Long storeIdx) {
		Store store = storeRepository.findById(storeIdx)
			.orElseThrow(() -> new StoreNotFoundException("식당을 찾을 수 없습니다. storeIdx : " + storeIdx));

		// 맛 유지
		Long changesWithTheMost = reviewRepository.getChangesWithTheMost(storeIdx);

		// 좋아요(%)
		Long totalCnt = reviewRepository.countByStore(store);
		Long likesCnt = reviewRepository.countByStoreAndLikes(store);

		// 베스트 메뉴
		Long bestMenuIdx = reviewRepository.getBestMenuIdx(storeIdx);
		Menu bestMenu = bestMenuIdx == null ?
			menuRepository.findFirstByStore(store) : menuRepository.getReferenceById(bestMenuIdx);

		// 키워드 목록
		List<Review> reviewList = reviewRepository.findByStoreOrderByCreatedAtDesc(store);
		List<Long> keywordList = reviewKeywordRepository.findKeywordsIn(reviewList);

		return new StoreBriefInfoResponse(
			storeIdx, store.getName(), changesWithTheMost, Math.round((float) likesCnt / totalCnt * 100), bestMenu.getName(), keywordList);
	}

	public StoreDetailInfoResponse getDetailInfo(Long storeIdx) {
		Store store = storeRepository.findById(storeIdx)
			.orElseThrow(() -> new StoreNotFoundException("식당을 찾을 수 없습니다. storeIdx : " + storeIdx));

		// 좋아요(%)
		Long totalCnt = reviewRepository.countByStore(store);
		Long likesCnt = reviewRepository.countByStoreAndLikes(store);

		// 메뉴 목록
		List<Menu> menuList = menuRepository.findByStore(store);

		List<Review> reviewList = reviewRepository.findByStoreOrderByCreatedAtDesc(store);
		List<ReviewDto> reviewDtoList = new ArrayList<>();
		for (Review review : reviewList) {
			List<Keyword> keywordList = reviewKeywordRepository.findByReview(review);
			reviewDtoList.add(ReviewDto.of(review, keywordList));
		}

		return StoreDetailInfoResponse.of(store, Math.round((float) likesCnt / totalCnt * 100), menuList, reviewDtoList);
	}

	public List<StoreSearchResponse> search(String query, List<Long> keyword, List<Region> region) {
		Set<Store> storeSet = new HashSet<>();
		if (query != null) {
			String[] searchWords = query.split(" ");
			for (String word : searchWords) {
				storeRepository.findDistinctByNameContains(word).stream().forEach(storeSet::add);
			}
		}
		if (keyword != null) {
			List<Keyword> keywordList = keywordRepository.findIn(keyword);
			List<Review> reviewList = reviewKeywordRepository.findReviewsIn(keywordList);
			for (Review review : reviewList) {
				storeSet.add(review.getStore());
			}
		}
		if (region != null) {
			storeRepository.findByRegionIn(region).stream().forEach(storeSet::add);
		}

		List<StoreSearchResponse> response = new ArrayList<>();
		for (Store store : storeSet) {
			// 좋아요(%)
			Long totalCnt = reviewRepository.countByStore(store);
			Long likesCnt = reviewRepository.countByStoreAndLikes(store);

			// 메뉴 목록
			List<Menu> menuList = menuRepository.findByStore(store);

			response.add(StoreSearchResponse.of(store, Math.round((float) likesCnt / totalCnt * 100), menuList));
		}

		return response;
	}

}
