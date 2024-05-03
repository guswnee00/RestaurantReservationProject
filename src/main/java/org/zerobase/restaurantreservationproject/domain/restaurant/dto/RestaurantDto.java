package org.zerobase.restaurantreservationproject.domain.restaurant.dto;

import lombok.*;
import org.zerobase.restaurantreservationproject.domain.restaurant.entity.RestaurantEntity;

import java.time.LocalDateTime;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class RestaurantDto {

    private String managerName;

    private String restaurantName;
    private String restaurantAddress;
    private String restaurantDetail;
                                            // TODO - repo 구현
    private String distance;                // 현재 위치와의 거리

    private double averageStarRating;       // 레스토랑의 평균 별점
    private Long reviewCount;               // 레스토랑의 리뷰 개수
    private List<Long> reviewIds;           // 레스토랑의 리뷰들의 pk 리스트

    private LocalDateTime createdAt;
    private LocalDateTime modifiedAt;

    public static RestaurantDto toDto(RestaurantEntity entity) {
        return RestaurantDto.builder()
                .managerName(entity.getManager().getUsername())
                .restaurantName(entity.getRestaurantName())
                .restaurantAddress(entity.getRestaurantAddress())
                .restaurantDetail(entity.getRestaurantDetail())
                // 거리, 평균 별점, 리뷰 개수, 리뷰 리스트는 나중에 set 으로 추가
                .createdAt(entity.getCreatedAt())
                .modifiedAt(entity.getModifiedAt())
                .build();
    }

}
