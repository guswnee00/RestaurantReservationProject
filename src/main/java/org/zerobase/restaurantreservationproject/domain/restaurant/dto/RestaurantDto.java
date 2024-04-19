package org.zerobase.restaurantreservationproject.domain.restaurant.dto;

import lombok.*;
import org.zerobase.restaurantreservationproject.domain.manager.entity.ManagerEntity;
import org.zerobase.restaurantreservationproject.domain.restaurant.entity.RestaurantEntity;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class RestaurantDto {

    private ManagerEntity id;

    private String restaurantName;
    private String restaurantAddress;
    private String restaurantDetail;

    private double lat;
    private double lnt;

    private double reviewGrade;
    private Long reviewAmount;

    private LocalDateTime createdAt;
    private LocalDateTime modifiedAt;

    public static RestaurantDto toDto(RestaurantEntity entity) {
        return RestaurantDto.builder()
                .id(entity.getId())
                .restaurantName(entity.getRestaurantName())
                .restaurantAddress(entity.getRestaurantAddress())
                .restaurantDetail(entity.getRestaurantDetail())
                .lat(entity.getLat())
                .lnt(entity.getLnt())
                .reviewGrade(entity.getReviewGrade())
                .reviewAmount(entity.getReviewAmount())
                .createdAt(entity.getCreatedAt())
                .modifiedAt(entity.getModifiedAt())
                .build();
    }
}
