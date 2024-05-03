package org.zerobase.restaurantreservationproject.domain.user.dto;

import lombok.*;
import org.zerobase.restaurantreservationproject.domain.user.entity.UserEntity;

import java.time.LocalDateTime;
import java.util.List;

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

    private List<Long> reservationIds;
    private List<Long> reviewIds;

    private LocalDateTime createdAt;
    private LocalDateTime modifiedAt;

    public static UserDto toDto(UserEntity entity) {
        return UserDto.builder()
                .username(entity.getUsername())
                .password(entity.getPassword())
                .phoneNumber(entity.getPhoneNumber())
                .role(entity.getRole())
                // 사용자가 예약한 내역들과 작성한 리뷰들은 나중에 set 으로 추가
                .createdAt(entity.getCreatedAt())
                .modifiedAt(entity.getModifiedAt())
                .build();
    }

}
