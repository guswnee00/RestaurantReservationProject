package org.zerobase.restaurantreservationproject.domain.restaurant.dto;

import lombok.*;
import org.zerobase.restaurantreservationproject.domain.manager.entity.ManagerEntity;
import org.zerobase.restaurantreservationproject.domain.reservation.entity.ReservationEntity;
import org.zerobase.restaurantreservationproject.domain.restaurant.entity.RestaurantEntity;
import org.zerobase.restaurantreservationproject.domain.review.entity.ReviewEntity;

import java.time.LocalDateTime;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class RestaurantDto {

    private Long id;

    private ManagerEntity manager;

    private String restaurantName;
    private String restaurantAddress;
    private String restaurantDetail;

    private double lat;
    private double lnt;
    private double distance;

    private List<ReservationEntity> reservations;

    private List<ReviewEntity> reviews;

    private LocalDateTime createdAt;
    private LocalDateTime modifiedAt;

    public static RestaurantDto toDto(RestaurantEntity entity) {
        return RestaurantDto.builder()
                .id(entity.getId())
                .manager(entity.getManager())
                .restaurantName(entity.getRestaurantName())
                .restaurantAddress(entity.getRestaurantAddress())
                .restaurantDetail(entity.getRestaurantDetail())
                .lat(entity.getLat())
                .lnt(entity.getLnt())
                .reservations(entity.getReservations())
                .reviews(entity.getReviews())
                .createdAt(entity.getCreatedAt())
                .modifiedAt(entity.getModifiedAt())
                .build();
    }

}
