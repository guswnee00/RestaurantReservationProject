package org.zerobase.restaurantreservationproject.domain.review.dto;

import lombok.*;
import org.zerobase.restaurantreservationproject.domain.restaurant.entity.RestaurantEntity;
import org.zerobase.restaurantreservationproject.domain.restaurant.repository.RestaurantRepository;
import org.zerobase.restaurantreservationproject.domain.review.entity.ReviewEntity;
import org.zerobase.restaurantreservationproject.domain.user.entity.UserEntity;
import org.zerobase.restaurantreservationproject.domain.user.repository.UserRepository;

import java.time.LocalDateTime;

public class ReviewAddition {

    @Getter
    @Setter
    @AllArgsConstructor
    public static class Request {

        private String username;
        private String restaurantName;

        private double starRating;
        private String comment;

        public static ReviewEntity toEntity(Request request, UserRepository userRepository, RestaurantRepository restaurantRepository) {

            UserEntity userEntity = userRepository.findByUsername(request.getUsername());
            RestaurantEntity restaurantEntity = restaurantRepository.findByRestaurantName(request.getRestaurantName());

            return ReviewEntity.builder()
                    .user(userEntity)
                    .restaurant(restaurantEntity)
                    .starRating(request.getStarRating())
                    .comment(request.getComment())
                    .build();
        }
    }

    @Getter
    @Setter
    @AllArgsConstructor
    @NoArgsConstructor
    @Builder
    public static class Response {

        private String username;
        private String restaurantName;

        private String starRating;
        private String comment;

        private LocalDateTime createdAt;

        public static Response fromDto(ReviewDto dto) {
            return Response.builder()
                    .username(dto.getUsername())
                    .restaurantName(dto.getRestaurantName())
                    .starRating(dto.getStarRating())
                    .comment(dto.getComment())
                    .createdAt(dto.getCreatedAt())
                    .build();
        }
    }

}
