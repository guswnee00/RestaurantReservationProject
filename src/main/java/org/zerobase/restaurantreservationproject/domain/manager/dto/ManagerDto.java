package org.zerobase.restaurantreservationproject.domain.manager.dto;

import lombok.*;
import org.zerobase.restaurantreservationproject.domain.manager.entity.ManagerEntity;
import org.zerobase.restaurantreservationproject.domain.restaurant.entity.RestaurantEntity;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class ManagerDto {

    private String managerId;
    private  String password;

    private String managerName;
    private String phoneNumber;

    private String role;

    private RestaurantEntity restaurantId;
    //private String restaurantName;

    private LocalDateTime createdAt;
    private LocalDateTime modifiedAt;

    public static ManagerDto toDto(ManagerEntity entity) {
        return ManagerDto.builder()
                .managerId(entity.getManagerId())
                .password(entity.getPassword())
                .managerName(entity.getManagerName())
                .phoneNumber(entity.getPhoneNumber())
                .role(entity.getRole())
                .createdAt(entity.getCreatedAt())
                .modifiedAt(entity.getModifiedAt())
                .build();
    }
}
