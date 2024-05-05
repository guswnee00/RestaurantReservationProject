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
    private String phoneNumber;
    private String restaurantName;

    private LocalDateTime reservationTime;
    private LocalDateTime arrivalTime;

    public UserArrival(ReservationDto dto) {
        this.username = dto.getUsername();
        this.restaurantName = dto.getRestaurantName();
        this.reservationTime = dto.getReservationDateTime();
        this.arrivalTime = LocalDateTime.now();
    }

}
