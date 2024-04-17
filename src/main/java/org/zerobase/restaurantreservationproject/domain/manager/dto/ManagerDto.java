package org.zerobase.restaurantreservationproject.domain.manager.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.zerobase.restaurantreservationproject.domain.restaurant.entity.RestaurantEntity;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
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
}
