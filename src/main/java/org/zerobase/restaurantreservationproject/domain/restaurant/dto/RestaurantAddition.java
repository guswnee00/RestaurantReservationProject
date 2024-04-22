package org.zerobase.restaurantreservationproject.domain.restaurant.dto;

import lombok.*;
import org.zerobase.restaurantreservationproject.domain.manager.entity.ManagerEntity;
import org.zerobase.restaurantreservationproject.domain.manager.repository.ManagerRepository;
import org.zerobase.restaurantreservationproject.domain.restaurant.entity.RestaurantEntity;

import java.time.LocalDateTime;

public class RestaurantAddition {

    @Getter
    @Setter
    @AllArgsConstructor
    public static class Request {
        private String managerId;

        private String restaurantName;
        private String restaurantAddress;
        private String restaurantDetail;

        private double lat;
        private double lnt;

        // TODO
        //  - error code 따로 만들어서 교체
        public static RestaurantEntity toEntity(Request request, ManagerRepository repository) {
            ManagerEntity manager = repository.findById(request.getManagerId())
                    .orElseThrow(() -> new IllegalArgumentException("Manager not found"));

            return RestaurantEntity.builder()
                    .managerId(request.getManagerId())
                    .manager(manager)
                    .restaurantName(request.getRestaurantName())
                    .restaurantAddress(request.getRestaurantAddress())
                    .restaurantDetail(request.getRestaurantDetail())
                    .lat(request.getLat())
                    .lnt(request.getLnt())
                    //.reviewGrade(0.0)
                    //.reviewAmount(0L)
                    .build();
        }
    }

    @Getter
    @Setter
    @AllArgsConstructor
    @NoArgsConstructor
    @Builder
    public static class Response {
        private String managerId;

        private String restaurantName;
        private String restaurantAddress;
        private String restaurantDetail;

        private LocalDateTime createdAt;

        public static Response fromDto(RestaurantDto dto) {
            return Response.builder()
                    .managerId(dto.getManagerId())
                    .restaurantName(dto.getRestaurantName())
                    .restaurantAddress(dto.getRestaurantAddress())
                    .restaurantDetail(dto.getRestaurantDetail())
                    .createdAt(dto.getCreatedAt())
                    .build();
        }
    }
}
