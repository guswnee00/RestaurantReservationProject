package org.zerobase.restaurantreservationproject.domain.reservation.dto;

import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.*;
import org.zerobase.restaurantreservationproject.domain.manager.entity.ManagerEntity;
import org.zerobase.restaurantreservationproject.domain.reservation.entity.ReservationEntity;
import org.zerobase.restaurantreservationproject.domain.reservation.status.ReservationStatus;
import org.zerobase.restaurantreservationproject.domain.restaurant.entity.RestaurantEntity;
import org.zerobase.restaurantreservationproject.domain.user.entity.UserEntity;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class ReservationDto {

    private Long id;

    private UserEntity user;

    private ManagerEntity manager;

    private RestaurantEntity restaurant;

    private Integer headCount;

    @Enumerated(EnumType.STRING)
    private ReservationStatus status;
    private LocalDateTime statusModifiedAt;

    private LocalDateTime reservationTime;

    public static ReservationDto toDto(ReservationEntity entity) {
        return ReservationDto.builder()
                .id(entity.getId())
                .user(entity.getUser())
                .manager(entity.getManager())
                .restaurant(entity.getRestaurant())
                .headCount(entity.getHeadCount())
                .status(entity.getStatus())
                .statusModifiedAt(entity.getStatusModifiedAt())
                .reservationTime(entity.getReservationTime())
                .build();
    }

}
