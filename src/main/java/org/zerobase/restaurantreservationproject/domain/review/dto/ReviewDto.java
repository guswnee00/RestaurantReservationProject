package org.zerobase.restaurantreservationproject.domain.review.dto;

import lombok.*;
import org.zerobase.restaurantreservationproject.domain.restaurant.entity.RestaurantEntity;
import org.zerobase.restaurantreservationproject.domain.review.entity.ReviewEntity;
import org.zerobase.restaurantreservationproject.domain.user.entity.UserEntity;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class ReviewDto {

    private Long id;

    private UserEntity user;

    private RestaurantEntity restaurant;

    private double starRating;
    private String comment;

    private LocalDateTime createdAt;
    private LocalDateTime modifiedAt;

    public static ReviewDto toDto(ReviewEntity entity) {
        return ReviewDto.builder()
                .id(entity.getId())
                .user(entity.getUser())
                .restaurant(entity.getRestaurant())
                .starRating(entity.getStarRating())
                .comment(entity.getComment())
                .createdAt(entity.getCreatedAt())
                .modifiedAt(entity.getModifiedAt())
                .build();
    }

}
