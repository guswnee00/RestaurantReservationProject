package org.zerobase.restaurantreservationproject.domain.manager.dto;

import lombok.*;
import org.zerobase.restaurantreservationproject.domain.manager.entity.ManagerEntity;

import java.time.LocalDateTime;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class ManagerDto {

    private String managerName;
    private String password;
    private String phoneNumber;

    private String role;
                                            // TODO - pk 리스트 repo 구현
    private String restaurantName;          // 소유한 레스토랑의 이름
    private List<Long> reservationIds;      // 소유한 레스토랑의 예약 pk 리스트

    private LocalDateTime createdAt;
    private LocalDateTime modifiedAt;

    public static ManagerDto toDto(ManagerEntity entity) {
        return ManagerDto.builder()
                .managerName(entity.getUsername())
                .password(entity.getPassword())
                .phoneNumber(entity.getPhoneNumber())
                .role(entity.getRole())
                .restaurantName(entity.getRestaurant().getRestaurantName())
                // 예약 리스트는 나중에 set 으로 추가
                .createdAt(entity.getCreatedAt())
                .modifiedAt(entity.getModifiedAt())
                .build();
    }

}
