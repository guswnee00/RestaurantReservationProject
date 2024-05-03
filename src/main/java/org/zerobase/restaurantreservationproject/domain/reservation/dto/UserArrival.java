package org.zerobase.restaurantreservationproject.domain.reservation.dto;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserArrival {

    private String username;
    private String restaurantName;

    private LocalDateTime reservationTime;
    private LocalDateTime arrivalTime;

    public UserArrival(ReservationDto dto) {
        this.username = dto.getUser().getUsername();
        this.restaurantName = dto.getRestaurant().getRestaurantName();
        this.reservationTime = dto.getReservationTime();
        this.arrivalTime = LocalDateTime.now();
    }

}
