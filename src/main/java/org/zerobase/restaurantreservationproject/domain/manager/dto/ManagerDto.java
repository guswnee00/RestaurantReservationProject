package org.zerobase.restaurantreservationproject.domain.manager.dto;

import lombok.*;
import org.zerobase.restaurantreservationproject.domain.manager.entity.ManagerEntity;
import org.zerobase.restaurantreservationproject.domain.reservation.entity.ReservationEntity;
import org.zerobase.restaurantreservationproject.domain.restaurant.entity.RestaurantEntity;

import java.time.LocalDateTime;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class ManagerDto {

    private Long id;

    private String username;
    private String password;
    private String phoneNumber;

    private String role;

    private RestaurantEntity restaurant;

    private List<ReservationEntity> reservations;

    private LocalDateTime createdAt;
    private LocalDateTime modifiedAt;

    public static ManagerDto toDto(ManagerEntity entity) {
        return ManagerDto.builder()
                .id(entity.getId())
                .username(entity.getUsername())
                .password(entity.getPassword())
                .phoneNumber(entity.getPhoneNumber())
                .role(entity.getRole())
                .restaurant(entity.getRestaurant())
                .reservations(entity.getReservations())
                .createdAt(entity.getCreatedAt())
                .modifiedAt(entity.getModifiedAt())
                .build();
    }

}
