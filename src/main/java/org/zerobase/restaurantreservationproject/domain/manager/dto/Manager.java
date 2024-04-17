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
        private String managerId;
        private String password;

        private String managerName;
        private String phoneNumber;

        private PersonRole role;

        public static ManagerEntity toEntity(Request request) {
            return ManagerEntity.builder()
                    .managerId(request.getManagerId())
                    .password(request.getPassword())
                    .managerName(request.getManagerName())
                    .phoneNumber(request.getPhoneNumber())
                    .role(PersonRole.MANAGER.toString())
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
        private String managerId;

        private String managerName;
        private String phoneNumber;

        private String role;

        private LocalDateTime createdAt;

        public static Response fromDto(ManagerDto dto) {
            return Response.builder()
                    .managerId(dto.getManagerId())
                    .managerName(dto.getManagerName())
                    .phoneNumber(dto.getPhoneNumber())
                    .role(dto.getRole())
                    .createdAt(dto.getCreatedAt())
                    .build();
        }
    }
}
