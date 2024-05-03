package org.zerobase.restaurantreservationproject.domain.review.dto;

import lombok.*;

import java.time.LocalDateTime;

public class ReviewModification {

    @Getter
    @Setter
    @AllArgsConstructor
    public static class Request {

        private String username;
        private String restaurantName;

        private double starRating;
        private String comment;

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

        private LocalDateTime modifiedAt;

        public static Response fromDto(ReviewDto dto) {
            return Response.builder()
                    .username(dto.getUsername())
                    .restaurantName(dto.getRestaurantName())
                    .starRating(dto.getStarRating())
                    .comment(dto.getComment())
                    .modifiedAt(dto.getModifiedAt())
                    .build();
        }

    }
}
