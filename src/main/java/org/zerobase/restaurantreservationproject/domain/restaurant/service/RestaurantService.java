package org.zerobase.restaurantreservationproject.domain.restaurant.service;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.zerobase.restaurantreservationproject.domain.restaurant.dto.RestaurantDetail;
import org.zerobase.restaurantreservationproject.domain.restaurant.dto.RestaurantSortingMethod;
import org.zerobase.restaurantreservationproject.domain.restaurant.entity.RestaurantEntity;
import org.zerobase.restaurantreservationproject.domain.restaurant.mapper.RestaurantMapper;
import org.zerobase.restaurantreservationproject.domain.restaurant.repository.RestaurantRepository;
import org.zerobase.restaurantreservationproject.global.error.CustomException;

import static org.zerobase.restaurantreservationproject.global.error.CustomErrorCode.RESTAURANT_DOES_NOT_EXIST;
import static org.zerobase.restaurantreservationproject.global.error.CustomErrorCode.RESTAURANT_NOT_REGISTERED;

@Service
@RequiredArgsConstructor
@Transactional
public class RestaurantService {

    private final RestaurantRepository restaurantRepository;

    // 임의의 위도, 경도 설정(광화문)
    private static final double MY_LATITUDE = 37.5760259;
    private static final double MY_LONGITUDE = 126.9768428;

    private static final int PAGE_SIZE = 10;

    /**
     * 레스토랑 이름으로 레스토랑 검색
     *      1. 레스토랑이 있는지 확인
     *      2. mapper 이용해서 entity -> dto 변환
     *      3. 내 위치와의 거리 setting
     *      4. 검색한 레스토랑의 상세정보 확인
     */
    public RestaurantDetail getRestaurantDetailByRestaurantName(String restaurantName) {
        RestaurantEntity restaurantEntity = checkRestaurantExist(restaurantName);

        RestaurantDetail restaurant = RestaurantMapper.INSTANCE.fromEntity(restaurantEntity);

        Double distance = restaurantRepository.findRestaurantByDistance(MY_LATITUDE, MY_LONGITUDE, restaurantName);
        restaurant.setDistance(distance);

        return restaurant;
    }

    private RestaurantEntity checkRestaurantExist(String restaurantName) {
        RestaurantEntity restaurantEntity = restaurantRepository.findByRestaurantName(restaurantName);
        if (restaurantEntity == null) {
            throw new CustomException(RESTAURANT_DOES_NOT_EXIST);
        }
        return restaurantEntity;
    }

    /**
     * 정렬 방식에 따른 레스토랑 정보
     *      1. 정렬 방식에 따라 레스토랑 정보 정렬
     *      2. null 인 경우 예외 발생
     *      3. 레스토랑 정보 page 리턴
     */
    public Page<RestaurantDetail> getRestaurantPage(RestaurantSortingMethod method, int page) {
        Pageable pageable = PageRequest.of(page, PAGE_SIZE);
        Page<RestaurantDetail> restaurantDetails = sortRestaurantBySortingMethod(method, pageable);

        if (restaurantDetails == null) {
            throw new CustomException(RESTAURANT_NOT_REGISTERED);
        }

        return restaurantDetails;
    }

    private Page<RestaurantDetail> sortRestaurantBySortingMethod(RestaurantSortingMethod method, Pageable pageable) {
        return switch (method.getSort()) {
            case NEAR_DISTANCE -> restaurantRepository
                    .findRestaurantsNearby(MY_LATITUDE, MY_LONGITUDE, pageable)
                    .map(RestaurantMapper.INSTANCE::fromEntity);

            case HIGH_STAR_RATING -> restaurantRepository
                    .findRestaurantsOrderByAverageStarRating(pageable)
                    .map(RestaurantMapper.INSTANCE::fromEntity);

            case ALPHABETIZATION -> restaurantRepository
                    .findAllByOrderByRestaurantNameAsc(pageable)
                    .map(RestaurantMapper.INSTANCE::fromEntity);

            case MANY_REVIEWS -> restaurantRepository
                    .findRestaurantsOrderByReviewCountDesc(pageable)
                    .map(RestaurantMapper.INSTANCE::fromEntity);
        };
    }
}
