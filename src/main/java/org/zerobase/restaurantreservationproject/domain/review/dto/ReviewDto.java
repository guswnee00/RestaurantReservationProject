package org.zerobase.restaurantreservationproject.domain.review.dto;

import lombok.*;
import org.zerobase.restaurantreservationproject.domain.review.entity.ReviewEntity;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class ReviewDto {

    private String username;

    private String restaurantName;
    private String starRating;
    private String comment;

    private LocalDateTime createdAt;
    private LocalDateTime modifiedAt;

    public static ReviewDto toDto(ReviewEntity entity) {
        return ReviewDto.builder()
                .username(entity.getUser().getUsername())
                .restaurantName(entity.getRestaurant().getRestaurantName())
                .starRating(String.format("%.1f", entity.getStarRating()))
                .comment(entity.getComment())
                .createdAt(entity.getCreatedAt())
                .modifiedAt(entity.getModifiedAt())
                .build();
    }

}
