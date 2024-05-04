package org.zerobase.restaurantreservationproject.domain.restaurant.dto;

import lombok.*;
import org.zerobase.restaurantreservationproject.domain.restaurant.entity.RestaurantEntity;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class RestaurantDetail {

    private String restaurantName;
    private String restaurantAddress;
    private String restaurantDetail;

    private double distance;                // 현재 위치와의 거리

    private double averageStarRating;       // 레스토랑의 평균 별점
    private Long reviewCount;               // 레스토랑의 리뷰 개수
    private List<String> reviewComments;    // 레스토랑의 리뷰들의 리스트

    public RestaurantDetail toDto(RestaurantEntity entity) {
        return RestaurantDetail.builder()
                .restaurantName(entity.getRestaurantName())
                .restaurantAddress(entity.getRestaurantAddress())
                .restaurantDetail(entity.getRestaurantDetail())
                // 나머지 정보는 추가로 set 할 예정
                .build();
    }

}
