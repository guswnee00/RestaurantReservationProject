package org.zerobase.restaurantreservationproject.domain.user.dto;

import lombok.*;
import org.zerobase.restaurantreservationproject.domain.reservation.entity.ReservationEntity;
import org.zerobase.restaurantreservationproject.domain.user.entity.UserEntity;

import java.time.LocalDateTime;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class UserDto {

    private Long id ;

    private String username;
    private String password;
    private String phoneNumber;

    private String role;

    private List<ReservationEntity> reservations;

    private LocalDateTime createdAt;
    private LocalDateTime modifiedAt;

    public static UserDto toDto(UserEntity entity) {
        return UserDto.builder()
                .id(entity.getId())
                .username(entity.getUsername())
                .password(entity.getPassword())
                .phoneNumber(entity.getPhoneNumber())
                .role(entity.getRole())
                .reservations(entity.getReservations())
                .createdAt(entity.getCreatedAt())
                .modifiedAt(entity.getModifiedAt())
                .build();
    }

}
