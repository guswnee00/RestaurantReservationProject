package org.zerobase.restaurantreservationproject.domain.user.dto;

import lombok.*;
import org.zerobase.restaurantreservationproject.domain.user.entity.UserEntity;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class UserDto {

    private String username;
    private String password;
    private String phoneNumber;

    private String role;

    private LocalDateTime createdAt;
    private LocalDateTime modifiedAt;

    public static UserDto toDto(UserEntity entity) {
        return UserDto.builder()
                .username(entity.getUsername())
                .password(entity.getPassword())
                .phoneNumber(entity.getPhoneNumber())
                .role(entity.getRole())
                .createdAt(entity.getCreatedAt())
                .modifiedAt(entity.getModifiedAt())
                .build();
    }

}
