package org.zerobase.restaurantreservationproject.domain.user.dto;

import lombok.*;
import org.zerobase.restaurantreservationproject.domain.user.entity.UserEntity;
import org.zerobase.restaurantreservationproject.global.role.PersonRole;

import java.time.LocalDateTime;

public class User {

    @Getter
    @Setter
    @AllArgsConstructor
    public static class Request {
        private String userId;
        private String password;

        private String userName;
        private String phoneNumber;

        private PersonRole role;

        public static UserEntity toEntity(Request request) {
            return UserEntity.builder()
                    .userId(request.getUserId())
                    .password(request.getPassword())
                    .userName(request.getUserName())
                    .phoneNumber(request.getPhoneNumber())
                    .role(PersonRole.USER.toString())
                    .createdAt(LocalDateTime.now())
                    .build();
        }
    }

    @Getter
    @Setter
    @AllArgsConstructor
    @NoArgsConstructor
    @Builder
    public static class Response {
        private String userId;

        private String userName;
        private String phoneNumber;

        private String role;

        private LocalDateTime createdAt;

        public static Response fromDto(UserDto dto) {
            return Response.builder()
                    .userId(dto.getUserId())
                    .userName(dto.getUserName())
                    .phoneNumber(dto.getPhoneNumber())
                    .role(dto.getRole())
                    .createdAt(dto.getCreatedAt())
                    .build();
        }
    }
}
