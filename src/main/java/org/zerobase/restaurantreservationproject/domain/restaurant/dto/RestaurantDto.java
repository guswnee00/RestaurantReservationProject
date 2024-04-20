package org.zerobase.restaurantreservationproject.domain.restaurant.dto;

import lombok.*;
import org.zerobase.restaurantreservationproject.domain.restaurant.entity.RestaurantEntity;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class RestaurantDto {

    private Long id;
    private String managerId;

    private String restaurantName;
    private String restaurantAddress;
    private String restaurantDetail;

    private double lat;
    private double lnt;

    // TODO
    //  - 리뷰 엔티티 추가
    //private double reviewGrade;
    //private Long reviewAmount;

    private LocalDateTime createdAt;
    private LocalDateTime modifiedAt;

    public static RestaurantDto toDto(RestaurantEntity entity) {
        return RestaurantDto.builder()
                .id(entity.getId())
                .managerId(entity.getManagerId())
                .restaurantName(entity.getRestaurantName())
                .restaurantAddress(entity.getRestaurantAddress())
                .restaurantDetail(entity.getRestaurantDetail())
                .lat(entity.getLat())
                .lnt(entity.getLnt())
                //.reviewGrade(entity.getReviewGrade())
                //.reviewAmount(entity.getReviewAmount())
                .createdAt(entity.getCreatedAt())
                .modifiedAt(entity.getModifiedAt())
                .build();
    }
}
