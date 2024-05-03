package org.zerobase.restaurantreservationproject.domain.reservation.dto;

import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.*;
import org.zerobase.restaurantreservationproject.domain.reservation.entity.ReservationEntity;
import org.zerobase.restaurantreservationproject.global.enumset.ReservationStatus;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class ReservationDto {

    private String username;
    private String managerName;
    private String restaurantName;

    private Integer headCount;

    @Enumerated(EnumType.STRING)
    private ReservationStatus status;
    private LocalDateTime statusModifiedAt;

    private LocalDateTime reservationTime;

    public static ReservationDto toDto(ReservationEntity entity) {
        return ReservationDto.builder()
                .username(entity.getUser().getUsername())
                .managerName(entity.getManager().getUsername())
                .restaurantName(entity.getRestaurant().getRestaurantName())
                .headCount(entity.getHeadCount())
                .status(entity.getStatus())
                .statusModifiedAt(entity.getStatusModifiedAt())
                .reservationTime(entity.getReservationTime())
                .build();
    }

}
