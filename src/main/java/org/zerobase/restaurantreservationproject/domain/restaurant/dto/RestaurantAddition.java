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
    @NoArgsConstructor
    @Builder
    public static class Request {

        private String managerName;

        private String restaurantName;
        private String restaurantAddress;
        private String restaurantDetail;

        private double lat;
        private double lnt;

        public static RestaurantEntity toEntity(Request request, ManagerRepository managerRepository) {

            // managerUsername(사장님아이디)로 managerEntity 찾기
            ManagerEntity managerEntity = managerRepository.findByUsername(request.getManagerName());

            return RestaurantEntity.builder()
                    .manager(managerEntity)
                    .restaurantName(request.getRestaurantName())
                    .restaurantAddress(request.getRestaurantAddress())
                    .restaurantDetail(request.getRestaurantDetail())
                    .lat(request.getLat())
                    .lnt(request.getLnt())
                    .build();
        }
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

        private LocalDateTime createdAt;

        public static Response fromDto(RestaurantDto dto) {
            return Response.builder()
                    .managerName(dto.getManagerName())
                    .restaurantName(dto.getRestaurantName())
                    .restaurantAddress(dto.getRestaurantAddress())
                    .restaurantDetail(dto.getRestaurantDetail())
                    .createdAt(dto.getCreatedAt())
                    .build();
        }
    }

}
