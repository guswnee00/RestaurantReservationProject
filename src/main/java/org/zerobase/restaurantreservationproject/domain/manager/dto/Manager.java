package org.zerobase.restaurantreservationproject.domain.manager.dto;

import lombok.*;
import org.zerobase.restaurantreservationproject.domain.manager.entity.ManagerEntity;
import org.zerobase.restaurantreservationproject.global.role.PersonRole;

import java.time.LocalDateTime;

public class Manager {

    @Getter
    @Setter
    @AllArgsConstructor
    public static class Request {

        private String username;
        private String password;
        private String phoneNumber;

        public static ManagerEntity toEntity(Request request) {
            return ManagerEntity.builder()
                    .username(request.getUsername())
                    .password(request.getPassword())
                    .phoneNumber(request.getPhoneNumber())
                    .role(PersonRole.ROLE_MANAGER.toString())
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

        private String username;
        private String phoneNumber;

        private String role;

        private LocalDateTime createdAt;

        public static Response fromDto(ManagerDto dto) {
            return Response.builder()
                    .username(dto.getUsername())
                    .phoneNumber(dto.getPhoneNumber())
                    .role(dto.getRole())
                    .createdAt(dto.getCreatedAt())
                    .build();
        }
    }

}
