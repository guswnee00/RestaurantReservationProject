package org.zerobase.restaurantreservationproject.domain.reservation.dto;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ReservationArrivalCheck {

    private String username;
    private String phoneNumber;

}
