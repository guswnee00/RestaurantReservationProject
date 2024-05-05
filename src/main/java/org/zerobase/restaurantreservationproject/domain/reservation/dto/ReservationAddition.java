package org.zerobase.restaurantreservationproject.domain.reservation.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.*;
import org.zerobase.restaurantreservationproject.global.enumset.ReservationStatus;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

public class ReservationAddition {

    @Getter
    @Setter
    @AllArgsConstructor
    public static class Request {

        private String username;
        private String phoneNumber;

        private String restaurantName;

        private Integer headCount;

        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy/MM/dd")
        private LocalDate reservationDate;
        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "HH:mm")
        private LocalTime reservationTime;

    }

    @Getter
    @Setter
    @AllArgsConstructor
    @NoArgsConstructor
    @Builder
    public static class Response {

        private String username;
        private String phoneNumber;

        private String restaurantName;

        private Integer headCount;

        @Enumerated(EnumType.STRING)
        private ReservationStatus status;
        private LocalDateTime statusModifiedAt;

        private LocalDateTime reservationTime;

        public static Response fromDto(ReservationDto dto) {
            return Response.builder()
                    .username(dto.getUsername())
                    .phoneNumber(dto.getPhoneNumber())
                    .restaurantName(dto.getRestaurantName())
                    .headCount(dto.getHeadCount())
                    .status(dto.getStatus())
                    .statusModifiedAt(dto.getStatusModifiedAt())
                    .reservationTime(dto.getReservationDateTime())
                    .build();
        }
    }

}
