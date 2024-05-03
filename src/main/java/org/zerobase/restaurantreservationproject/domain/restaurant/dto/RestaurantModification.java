package org.zerobase.restaurantreservationproject.domain.restaurant.dto;

import lombok.*;

import java.time.LocalDateTime;

public class RestaurantModification {

    @Getter
    @Setter
    @AllArgsConstructor
    public static class Request {

        private String managerName;

        private String restaurantName;
        private String restaurantAddress;
        private String restaurantDetail;

        private double lat;
        private double lnt;

    }

    @Getter
    @Setter
    @AllArgsConstructor
    @NoArgsConstructor
    @Builder
    public static class Response {

        private String managerName;

        private String restaurantName;
        private String restaurantAddress;
        private String restaurantDetail;

        private LocalDateTime modifiedAt;

        public static Response fromDto(RestaurantDto dto) {
            return Response.builder()
                    .managerName(dto.getManagerName())
                    .restaurantName(dto.getRestaurantName())
                    .restaurantAddress(dto.getRestaurantAddress())
                    .restaurantDetail(dto.getRestaurantDetail())
                    .modifiedAt(dto.getModifiedAt())
                    .build();
        }
    }

}
